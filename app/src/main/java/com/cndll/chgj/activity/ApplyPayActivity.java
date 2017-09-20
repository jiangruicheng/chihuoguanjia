package com.cndll.chgj.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cndll.chgj.R;
import com.cndll.chgj.mvp.MObeserver;
import com.cndll.chgj.mvp.mode.AppRequest;
import com.cndll.chgj.mvp.mode.bean.info.AppMode;
import com.cndll.chgj.mvp.mode.bean.request.RequestUpLoadPayInfo;
import com.cndll.chgj.mvp.mode.bean.response.BaseResponse;
import com.cndll.chgj.mvp.mode.bean.response.ResponseArea;
import com.cndll.chgj.mvp.mode.bean.response.ResponseBank;
import com.cndll.chgj.mvp.mode.bean.response.ResponseBranchBank;
import com.cndll.chgj.mvp.mode.bean.response.ResponseUploadImage;
import com.cndll.chgj.mvp.presenter.BasePresenter;
import com.cndll.chgj.mvp.view.BaseView;
import com.cndll.chgj.util.ImageUtil;
import com.cndll.chgj.util.Md5Utils;
import com.cndll.chgj.util.PopUpViewUtil;
import com.cndll.chgj.weight.MesgShow;
import com.cndll.chgj.weight.OptionPickView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.widget.Toast.makeText;

public class ApplyPayActivity extends AppCompatActivity {

    @BindView(R.id.back)
    Button back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_right)
    TextView titleRight;
    @BindView(R.id.title_tow)
    LinearLayout titleTow;
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.idcard_face)
    ImageView idcardFace;
    @BindView(R.id.storyID)
    TextView storyID;
    @BindView(R.id.textView16)
    TextView textView16;
    @BindView(R.id.textView15)
    TextView textView15;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.id_card)
    EditText idCard;
    @BindView(R.id.idcard_time)
    EditText idcardTime;
    @BindView(R.id.cast)
    EditText cast;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.shop_name)
    EditText shopName;
    @BindView(R.id.shop_nick)
    EditText shopNick;
    @BindView(R.id.bankcard_face)
    ImageView bankcardFace;

    @OnClick(R.id.bankcard_face)
    void onclick_bankCardFace() {
        getImage();
        selectImageView = bankcardFace;
        imagePosition = 3;
    }

    @BindView(R.id.bankcard_back)
    ImageView bankcardBack;

    @OnClick(R.id.bankcard_back)
    void onclick_bankCardBack() {
        getImage();
        selectImageView = bankcardBack;
        imagePosition = 4;
    }

    @BindView(R.id.checkstand)
    ImageView checkstand;

    @OnClick(R.id.checkstand)
    void onclick_checkstand() {
        getImage();
        selectImageView = checkstand;
        imagePosition = 5;
    }

    @OnClick(R.id.storyID)
    void onclick_storyID() {
        if (parentBankID.equals("")) {
            MesgShow.showMesg("", "请选择银行", sure, null, null, false);
            return;
        }
        AppRequest.getAPI().getBranchBankList("0", parentBankID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseview) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(final BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse.getCode() == 200) {
                    final OptionPickView optionPickView = new OptionPickView(ApplyPayActivity.this);
                    ArrayList<String> bankname = new ArrayList<String>();
                    for (int i = 0; i < ((ResponseBranchBank) baseResponse).getData().size(); i++) {
                        bankname.add(((ResponseBranchBank) baseResponse).getData().get(i).getBank_name());
                    }
                    optionPickView.setOptionItem(bankname);
                    optionPickView.setLooper(false, false);
                    optionPickView.setOnOptionPickViewSelect(new OptionPickView.OnOptionPickViewSelect() {
                        @Override
                        public void onSelect(int sheng, int shi) {
                            storyID.setText(((ResponseBranchBank) baseResponse).getData().get(sheng).getBank_name());
                            branhBankNo = ((ResponseBranchBank) baseResponse).getData().get(sheng).getBank_no();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                    optionPickView.show();

                }
            }
        });
    }

    @BindView(R.id.tel)
    EditText tel;
    @BindView(R.id.bankcard)
    EditText bankcard;
    @BindView(R.id.bankcard_username)
    EditText bankcardUsername;
    @BindView(R.id.bank_name)
    TextView bankName;
    String parentBankID = "";

    @OnClick(R.id.bank_name)
    void onclick_bankname() {
        AppRequest.getAPI().getBankList("0").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseview) {
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(final BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse.getCode() == 200) {
                    final OptionPickView optionPickView = new OptionPickView(ApplyPayActivity.this);
                    ArrayList<String> bankname = new ArrayList<String>();
                    for (int i = 0; i < ((ResponseBank) baseResponse).getData().size(); i++) {
                        bankname.add(((ResponseBank) baseResponse).getData().get(i).getParent_bank_name());
                    }
                    optionPickView.setOptionItem(bankname);
                    optionPickView.setLooper(false, false);
                    optionPickView.setOnOptionPickViewSelect(new OptionPickView.OnOptionPickViewSelect() {
                        @Override
                        public void onSelect(int sheng, int shi) {
                            bankName.setText(((ResponseBank) baseResponse).getData().get(sheng).getParent_bank_name());
                            parentBankID = ((ResponseBank) baseResponse).getData().get(sheng).getParent_bank_no();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                    optionPickView.show();

                }
            }
        });
    }

    @BindView(R.id.bank_adrress)
    TextView bankAdrress;
    ArrayList<String> item0 = new ArrayList<>();
    List<List<String>> item1 = new ArrayList<>();
    ArrayList<String> item1_ = null;

    @OnClick(R.id.bank_adrress)
    void onclick_bankadrress() {

        AppRequest.getAPI().getBankArea("payapply").map(new Func1<ResponseArea, ResponseArea>() {
            @Override
            public ResponseArea call(ResponseArea responseArea) {
                List<ResponseArea.DataBean> counAddresses = responseArea.getData();
                for (int i = 0; i < counAddresses.size(); i++) {
                    item0.add(counAddresses.get(i).getName());
                    item1_ = new ArrayList<String>();
                    for (int j = 0; j < counAddresses.get(i).getCitys().size(); j++) {
                        item1_.add(counAddresses.get(i).getCitys().get(j).getName());
                    }
                    item1.add(item1_);
                }

                return responseArea;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseArea>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(final ResponseArea responseArea) {
                        OptionPickView optionPickView;

                        optionPickView = new OptionPickView(ApplyPayActivity.this, R.layout.dialog_opitionpick);
                        optionPickView.setLooper(false, false);
                        optionPickView.setOptionItem(item0, item1);
                        optionPickView.show();
                        if (optionPickView.getOnOptionPickViewSelect() == null) {
                            optionPickView.setOnOptionPickViewSelect(new OptionPickView.OnOptionPickViewSelect() {
                                @Override
                                public void onSelect(int sheng, int shi) {
                                    ApplyPayActivity.this.sheng = Integer.valueOf(responseArea.getData().get(sheng).getId());
                                    ApplyPayActivity.this.shi = Integer.valueOf(responseArea.getData().get(sheng).getCitys().get(shi).getId());
                                    bankAdrress.setText(responseArea.getData().get(sheng).getName() + "-" + responseArea.getData().get(sheng).getCitys().get(shi).getName());

                                }

                                @Override
                                public void onCancel() {

                                }
                            });
                        }
                    }
                });
    }

    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.sure)
    Button sure;

    @OnClick(R.id.sure)
    void onlcik_sure() {
        for (int i = 0; i < paths.length; i++) {
            if (paths[i] == null || paths[i].equals(" ")) {
                return;
            }
        }
        baseShowProg(sure);
        upLoadImage(0);
        upLoadImage(1);
        upLoadImage(2);
        upLoadImage(3);
        upLoadImage(4);
        upLoadImage(5);
    }

    @OnClick(R.id.idcard_face)
    void onclick_idcardface() {
        getImage();
        selectImageView = idcardFace;
        imagePosition = 0;
    }

    @BindView(R.id.idcard_back)
    ImageView idcardBack;

    @OnClick(R.id.idcard_back)
    void onclick_idcardback() {
        getImage();
        selectImageView = idcardBack;
        imagePosition = 1;
    }

    @BindView(R.id.business_licence)
    ImageView businessLicence;

    @OnClick(R.id.business_licence)
    void onclick_business() {
        getImage();
        selectImageView = businessLicence;
        imagePosition = 2;
    }

    private void getImage() {
        SelectImage selectImage = new SelectImage();
        selectImage.init(sure);
    }

    PopUpViewUtil prog;

    protected void baseShowProg(View location) {
        if (prog == null) {
            prog = PopUpViewUtil.getInstance();
            prog.setOnDismissAction(new PopUpViewUtil.OnDismissAction() {
                @Override
                public void onDismiss() {
                    prog = null;
                }
            });
            View view = LayoutInflater.from(this).inflate(R.layout.progress, null, false);
            prog.popListWindowNotOut(location, view, prog.getWindowManager(this).getDefaultDisplay().getWidth() / 7, prog.getWindowManager(this).getDefaultDisplay().getHeight() / 10, Gravity.CENTER, null);
        }

    }

    protected boolean baseDisProg() {
        if (prog != null) {
            prog.dismiss();
            prog = null;
            return true;
        }
        return false;
    }

    BaseView baseview = new BaseView() {
        @Override
        public void showMesg(String mesg) {
            MesgShow.showMesg("", mesg, sure, new MesgShow.OnButtonListener() {
                @Override
                public void onListerner() {

                }
            }, new MesgShow.OnButtonListener() {
                @Override
                public void onListerner() {

                }
            }, false);
        }

        @Override
        public void showProg(String mesg) {

        }

        @Override
        public void disProg() {

        }

        @Override
        public void toast(String s) {

        }

        @Override
        public void setPresenter(BasePresenter presenter) {

        }
    };
    private ImageView selectImageView;
    private String[] paths = new String[6];
    private int imagePosition;
    private String[] imageData = new String[6];
    private int statue = 0;
    private String branhBankNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_pay);
        ButterKnife.bind(this);
        title.setText("支付申请");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private File getTempImage(int position) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File tempFile = null;
            switch (position) {
                case 0:
                    tempFile = new File(Environment.getExternalStorageDirectory(), "face.jpg");
                    break;
                case 1:
                    tempFile = new File(Environment.getExternalStorageDirectory(), "back.jpg");
                    break;
                case 2:
                    tempFile = new File(Environment.getExternalStorageDirectory(), "business.jpg");
                    break;
                case 3:
                    tempFile = new File(Environment.getExternalStorageDirectory(), "bankface.jpg");
                    break;
                case 4:
                    tempFile = new File(Environment.getExternalStorageDirectory(), "bankback.jpg");
                    break;
                case 5:
                    tempFile = new File(Environment.getExternalStorageDirectory(), "checkstand.jpg");
                    break;
            }
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tempFile;
        }
        return null;
    }

    int sheng, shi;

    private void upLoadImage(final int position) {
        Map<String, RequestBody> parmes = new HashMap<String, RequestBody>();
        File file = new File(paths[position]);
        parmes.put("fmd", toreRequestBody(Md5Utils.getFileMD5(file.getPath())));
        Log.i("MD5", file.getName() + ": " + Md5Utils.getFileMD5(file.getPath()));
        final String s = Md5Utils.getFileMD5(file.getPath());
        parmes.put("file\";filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
        AppRequest.getAPI().uploadImage(parmes).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new MObeserver(baseview) {
            @Override
            public void onCompleted() {
                baseDisProg();
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(BaseResponse baseResponse) {
                super.onNext(baseResponse);
                if (baseResponse.getCode() == 1) {
                    imageData[position] = ((ResponseUploadImage) baseResponse).getData();
                    statue++;
                    if (statue == 6) {
                        statue = 0;
                        baseShowProg(sure);
                        AppRequest.getAPI().uploadPayInfo(new RequestUpLoadPayInfo().
                                setUid(AppMode.getInstance().getUid()).
                                setMid(AppMode.getInstance().getMid()).
                                setProvince_id(sheng + "").setCity_id(shi + "").
                                setBanknum(bankcard.getText().toString()).
                                setBankaddress(bankName.getText().toString() + "-" + storyID.getText().toString()).
                                setCode(AppMode.getInstance().getMcode()).
                                setTel(tel.getText().toString()).
                                setName(bankcardUsername.getText().toString()).setCert_1(imageData[0]).setCert_2(imageData[1]).setCert_3(imageData[2]).setCert_4(imageData[3]).setCert_5(imageData[4]).setCert_6(imageData[5])
                                .setBank_no(branhBankNo).
                                        setBank_name(storyID.getText().toString()).
                                        setMerchant_name(shopName.getText().toString()).
                                        setMerchant_alias(shopNick.getText().toString()).
                                        setMerchant_company(cast.getText().toString()).
                                        setMerchant_address(address.getText().toString()).
                                        setMerchant_province_code(sheng + "").
                                        setMerchant_city_code(shi + "").
                                        setMerchant_email(email.getText().toString()).
                                        setMerchant_id_no(idCard.getText().toString()).
                                        setMerchant_id_expire(idcardTime.getText().toString()).
                                        setAccount_name(bankcardUsername.getText().toString())

                        ).
                                subscribeOn(Schedulers.io()).
                                observeOn(AndroidSchedulers.mainThread()).
                                subscribe(new MObeserver(baseview) {
                                    @Override
                                    public void onCompleted() {
                                        super.onCompleted();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        super.onError(e);
                                    }

                                    @Override
                                    public void onNext(BaseResponse baseResponse) {
                                        super.onNext(baseResponse);
                                        baseDisProg();
                                        if (baseResponse.getCode() == 1) {
                                            makeText(ApplyPayActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                                            ApplyPayActivity.this.finish();
                                        } else {
                                            MesgShow.showMesg("", baseResponse.extra, sure, null, null, false);
                                        }
                                    }
                                });
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x01:
                File f = getTempImage(imagePosition);
                File file1 = new File(Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS) + "thunder" + f.getName());
                paths[imagePosition] = file1.getPath();
                ImageUtil.compressImage(f.getPath(), file1.getPath(), 10);
                Bitmap bitmap = getScaleBitmap(ApplyPayActivity.this, paths[imagePosition]);
                if (null != bitmap) {
                    selectImageView.setImageBitmap(bitmap);
                }
                f.delete();
                break;
            case 0x02:
                if (data == null) {
                    return;
                }
                if (data.getData() == null) {
                    return;
                }
                File f1 = new File(getRealPathFromURI(data.getData()));
                File file2 = new File(Environment.getExternalStoragePublicDirectory
                        (Environment.DIRECTORY_DOWNLOADS) + "thunder" + f1.getName());

                ImageUtil.compressImage(f1.getPath(), file2.getPath(), 10);
                paths[imagePosition] = file2.getPath();
                Bitmap bitmap1 = getScaleBitmap(ApplyPayActivity.this, f1.getPath());
                selectImageView.setImageBitmap(bitmap1);
                break;
        }
    }

    @NonNull
    private RequestBody toreRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @Override
    public void finish() {
        super.finish();

        /*for (int i = 0; i < paths.length; i++) {
            if (paths[i] == null) {
                continue;
            }
            File file = new File(paths[i]);
            if (file.getName().equals("face.jpg") || file.getName().equals("back.jpg") || file.getName().equals("business.jpg")) {
                file.delete();
            }
        }*/
    }

    private String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private Bitmap getScaleBitmap(Context ctx, String filePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, opt);

        int bmpWidth = opt.outWidth;
        int bmpHeght = opt.outHeight;

        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        opt.inSampleSize = 1;
        if (bmpWidth > bmpHeght) {
            if (bmpWidth > screenWidth)
                opt.inSampleSize = bmpWidth / screenWidth;
        } else {
            if (bmpHeght > screenHeight)
                opt.inSampleSize = bmpHeght / screenHeight;
        }
        opt.inJustDecodeBounds = false;

        bmp = BitmapFactory.decodeFile(filePath, opt);
        return bmp;
    }

    public class SelectImage {
        View view;
        PopUpViewUtil popUpViewUtil;
        @BindView(R.id.carma)
        TextView carma;
        @BindView(R.id.photo)
        TextView photo;
        @BindView(R.id.cancel)
        TextView cancel;
        Unbinder unbinder;

        public void init(View location) {
            popUpViewUtil = PopUpViewUtil.getInstance();
            view = LayoutInflater.from(ApplyPayActivity.this).inflate(R.layout.popview_addimage, null, false);
            unbinder = ButterKnife.bind(this, view);
            carma.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File file = getTempImage(imagePosition);
                        Uri uri = Uri.fromFile(file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, 0x01);
                    }
                    popUpViewUtil.dismiss();
                }
            });
            photo.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");//相片类型
                    startActivityForResult(intent, 0x02);
                    popUpViewUtil.dismiss();
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popUpViewUtil.dismiss();
                }
            });
           /* popUpViewUtil.popListWindow(location, view, popUpViewUtil.getWindowManager(ApplyPayActivity.this).

                    getDefaultDisplay().

                    getWidth(), popUpViewUtil.

                    getWindowManager(ApplyPayActivity.this).

                    getDefaultDisplay().

                    getHeight() / 3, Gravity.BOTTOM, null);*/
            popUpViewUtil.showDialog(ApplyPayActivity.this, view,
                    0, popUpViewUtil.getWindowManager(ApplyPayActivity.this).getDefaultDisplay().getHeight() / 3,
                    popUpViewUtil.getWindowManager(ApplyPayActivity.this).getDefaultDisplay().getWidth(),
                    popUpViewUtil.getWindowManager(ApplyPayActivity.this).getDefaultDisplay().getHeight() / 2, R.style.Translucent_Dialog);
        }
    }
}
