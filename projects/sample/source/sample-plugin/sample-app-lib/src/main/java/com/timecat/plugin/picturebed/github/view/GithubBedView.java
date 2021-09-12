package com.timecat.plugin.picturebed.github.view;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.tencent.shadow.sample.plugin.app.lib.R;
import com.timecat.plugin.picturebed.github.store.DEF;
import com.timecat.plugin.picturebed.github.store.GithubSetting;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author zby
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2020/4/13
 * @description null
 * @usage null
 */

public class GithubBedView {

    private ArrayList<Bitmap> arrayListBitmap = new ArrayList<>();
    private ArrayList<Integer> arrayListOrientation = new ArrayList<>();
    private ArrayList<Uri> arrayListUri = new ArrayList<>();
    private GridView gridView;
    private GridViewGalleryAdapter gridViewGalleryAdapter;

    private ImageView imageView;
    private ImageButton imgBtnBack;
    private Button btnSelect;
    private Button btnUpload;
    private TextView tvCount;
    private EditText urlTv;

    private EditText ownerEt;
    private EditText repoEt;
    private EditText emailEt;
    private EditText tokenEt;
    private EditText pathEt;
    private ImageButton imageButtonSettingBack;

    private ViewFlipper flipper;

    private Context context;
    private Uri imgToUpload = null;

    public GithubBedView(Context context, FrameLayout parent) {
        this.context = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater == null) return;
        View publicView = layoutInflater.inflate(R.layout.activity_main, parent, true);
        flipper = publicView.findViewById(R.id.vs);
        bindMainView(publicView);
        bindUploadView(publicView);
        bindSettingView(publicView);
        switchView(0);
    }

    private void bindSettingView(View publicView) {
        ownerEt = publicView.findViewById(R.id.owner);
        repoEt = publicView.findViewById(R.id.repo);
        emailEt = publicView.findViewById(R.id.email);
        tokenEt = publicView.findViewById(R.id.token);
        pathEt = publicView.findViewById(R.id.path);

        bindEt(ownerEt, GithubSetting.owner);
        bindEt(repoEt, GithubSetting.repo);
        bindEt(emailEt, GithubSetting.email);
        bindEt(tokenEt, GithubSetting.token);
        bindEt(pathEt, GithubSetting.path);

        imageButtonSettingBack = publicView.findViewById(R.id.imageButtonGalleryBack);
        imageButtonSettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView(0);
            }
        });
    }

    private void bindEt(final EditText editText, final GithubSetting setting) {
        String text =setting.defaultValue;// DEF.githubApp().getString(setting.key, setting.defaultValue);
        editText.setText(text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                DEF.githubApp().putString(setting.key, editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void bindMainView(View publicView) {
        gridView = publicView.findViewById(R.id.gridView);
        String[] projection = new String[]{"_id", "orientation"};
        int index = -1;
        Cursor cursor = context.getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        projection,
                        null,
                        null,
                        "_id DESC");
        if (cursor != null) {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.mipmap.gallery_thumb);
            while (cursor.moveToNext()) {
                index++;

                arrayListBitmap.add(b);
                try {
                    String ori = cursor.getString(cursor.getColumnIndexOrThrow("orientation"));
                    arrayListOrientation.add(Integer.parseInt(ori));
                } catch (Exception e) {
                    arrayListOrientation.add(0);
                }
                String path = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
                Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, path);
                arrayListUri.add(uri);
                new GenerateThumbAsync().execute(uri, index);
            }
            cursor.close();
        }
        gridViewGalleryAdapter = new GridViewGalleryAdapter(arrayListBitmap, context,
                R.layout.layout_gallery_griditem);
        gridView.setAdapter(gridViewGalleryAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                imgToUpload = arrayListUri.get(position);
                urlTv.setText(imgToUpload.getPath());
                imageView.setImageBitmap(getThumbnail(imgToUpload, position, 1024));
                switchView(1);
            }
        });
        tvCount = publicView.findViewById(R.id.textViewGalleryCount);
        tvCount.setText(arrayListBitmap.size() + " images");
    }

    private void bindUploadView(View publicView) {
        btnSelect = publicView.findViewById(R.id.pick);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView(0);
            }
        });
        btnUpload = publicView.findViewById(R.id.upload);
        urlTv = publicView.findViewById(R.id.url);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUpload.setEnabled(false);
                upload();
            }
        });
        imageView = publicView.findViewById(R.id.imageView);
        imgBtnBack = publicView.findViewById(R.id.imageButtonGalleryBack);
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView(0);
            }
        });
    }

    private void upload() {
        new UploadWork(context).work(imgToUpload.getPath(), new Work() {
            @Override
            public void beforeUpload(View view) {

            }

            @Override
            public void uploading() {

            }

            @Override
            public void uploadDone(String s) {
                btnUpload.setEnabled(true);
                urlTv.setText(s);
            }

            @Override
            public void uploadFail(String s) {
                btnUpload.setEnabled(true);
                urlTv.setText(s);
            }
        });
    }

    public Bitmap getThumbnail(Uri uri, int index, int size) {
        try {
            InputStream input = context.getContentResolver().openInputStream(uri);
            BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
            onlyBoundsOptions.inJustDecodeBounds = true;
            onlyBoundsOptions.inDither = true;
            onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
            input.close();
            if (onlyBoundsOptions.outWidth == -1 || onlyBoundsOptions.outHeight == -1) {
                return null;
            }
            int originalSize = Math.max(onlyBoundsOptions.outHeight, onlyBoundsOptions.outWidth);
            double ratio = 1.0d;
            if (size != 0) {
                ratio = originalSize > size ? (double) (originalSize / size) : 1.0d;
            }
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
            bitmapOptions.inDither = true;
            bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
            input = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            input.close();
            return getRotatedBitmap(context, uri, bitmap, index);
        } catch (Exception e) {
            return BitmapFactory.decodeResource(context.getResources(), R.mipmap.gallery_thumb);
        }
    }

    private int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) {
            return 1;
        }
        return k;
    }

    public Bitmap getRotatedBitmap(Context context, Uri photoUri, Bitmap img, int index) {
        Matrix matrix = new Matrix();
        switch (arrayListOrientation.get(index)) {
            case 90:
                matrix.postRotate(90.0f);
                break;
            case 180:
                matrix.postRotate(180.0f);
                break;
            case 270:
                matrix.postRotate(270.0f);
                break;
        }
        return Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
    }

    private void switchView(int index) {
        flipper.setDisplayedChild(index);
    }

    public void showSetting() {
        switchView(2);
    }

    public void showHome() {
        switchView(0);
    }

    class GenerateThumbAsync extends AsyncTask<Object, Void, Bitmap> {

        int index = 0;

        GenerateThumbAsync() {
        }

        protected Bitmap doInBackground(Object... params) {
            Uri uri = (Uri) params[0];
            index = (Integer) params[1];
            return getThumbnail(uri, index, 196);
        }

        protected void onPostExecute(Bitmap result) {
            arrayListBitmap.set(index, result);
            gridViewGalleryAdapter.refreshItems();
        }
    }
}