package com.eaapps.myapplication;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import com.eaapps.myapplication.databinding.AcConfirmationOrderBinding;
import com.eaapps.myapplication.databinding.MethodBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    AcConfirmationOrderBinding acBinding;

    private ProductAdapter productAdapter;
    List<Products> products = Arrays.asList(
            new Products(1, 1, "صندوق خيار", "كيلوا واحد فقط ، تقريبا ٨ الى ١٠ حبات", R.drawable.products1, 14.25),
            new Products(2, 6, "صندوق الأهل", "عدد ٦ صندوق الأهل ب ٤٨٣ ريال سعودي فقط", R.drawable.products2, 483.00),
            new Products(3, 6, "صندوق البيت", "عدد ٦ صندوق الأهل ب 98 ريال سعودي فقط", R.drawable.products3, 98.6),
            new Products(4, 2, "صندوق خيار", "اثنين كيلو فقط ، تقريبا ۱٦ الى ۲٠ حبات", R.drawable.products4, 28.50),
            new Products(5, 3, "صندوق الأهل", "عدد ۳ صندوق الأهل ب ۲٤۱ ريال سعودي فقط", R.drawable.products5, 241.00),
            new Products(6, 6, "صندوق البيت", "عدد ٦ صندوق الأهل ب ۹۸ ريال سعودي فقط", R.drawable.products6, 98.00)
    );

    private boolean expandable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setAppLocale();
         super.onCreate(savedInstanceState);
        acBinding = DataBindingUtil.setContentView(this, R.layout.ac_confirmation_order);
        setupListProducts();
        acBinding.collapseGroup.setOnClickListener(v -> {
            if (expandable)
                collapse();
            else
                extendable();
        });
        setupRadioGroup();
        preparedData();
        setupToolbar();

    }

    private void setupToolbar(){
        acBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String lang=  getSharedPreferences("RA",MODE_PRIVATE).getString("LANG","ar");
                 if (lang.equals("ar")){
                    saveLang("en");
                 }else{
                    saveLang("ar");
                }
                recreate();
                return false;
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setupListProducts() {
        productAdapter = new ProductAdapter(products);
        acBinding.products.setHasFixedSize(false);
        acBinding.products.setAdapter(productAdapter);
        acBinding.currency.setText("3910 ر.س");
        collapse();
    }

    @SuppressLint("SetTextI18n")
    private void collapse() {
        productAdapter.collapse();
        expandable = false;
        acBinding.collapseIcon.setVisibility(View.GONE);
        acBinding.collapseTitle.setText(" +4 " + getString(R.string.products));
        productAdapter.notifyDataSetChanged();
    }

    private void extendable() {
        productAdapter.extendable();
        expandable = true;
        acBinding.collapseIcon.setVisibility(View.VISIBLE);
        acBinding.collapseTitle.setText(getString(R.string.hide));
        productAdapter.notifyDataSetChanged();
    }


    private void saveLang(String lang){
        getSharedPreferences("RA",MODE_PRIVATE).edit().putString("LANG",lang).apply();
    }

    private void setAppLocale() {
        String lang=  getSharedPreferences("RA",MODE_PRIVATE).getString("LANG","ar");
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.setLocale(new Locale(lang));
        resources.updateConfiguration(config, dm);
    }

    private void setupRadioGroup() {
        acBinding.paymentMethods.groupRadio.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.visa) {
                setupPermissionBottomSheet();
            }
        });
    }

    private void setupPermissionBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        MethodBottomSheetBinding viewBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.method_bottom_sheet,
                null, false);
        bottomSheetDialog.setContentView(viewBinding.getRoot());
        bottomSheetDialog.show();
        viewBinding.visaMadaListRecyclerView.setAdapter(new MethodItemAdapter(
                        Arrays.asList(new Cards("4158482126678988", R.drawable.visa),
                                new Cards("4158482126671495", R.drawable.mada))
                        , cards -> {
                    selectCard(cards);
                    bottomSheetDialog.dismiss();
                }
                )

        );
    }

    private void preparedData(){
        acBinding.deliveryTo.subTitle.setTextColor(Color.BLACK);
        acBinding.deliveryTo.setSubTitle("البيت (طريق الحسن بن علي ,الرياض)");
        acBinding.wallet.subTitle.setTextColor(Color.BLACK);

    }

    private void selectCard(Cards cards) {
        acBinding.visaDetail.setGroupVisible(true);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.bold);
        acBinding.visaDetail.title.setTypeface(typeface);
        acBinding.visaDetail.title.setText(cards.number);
        acBinding.visaDetail.icon.setImageResource(cards.image);
        acBinding.visaDetail.changeBtn.setOnClickListener(v-> setupPermissionBottomSheet());
        acBinding.wallet.SwitchBtn.setChecked(true);
        acBinding.timeSlot.subTitle.setTextColor(Color.BLACK);
        acBinding.timeSlot.setSubTitle("اليوم 09:00 ص - 12:00 م");
        acBinding.completeOrder.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#48B976")));
    }


}