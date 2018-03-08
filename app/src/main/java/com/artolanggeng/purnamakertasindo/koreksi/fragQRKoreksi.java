package com.artolanggeng.purnamakertasindo.koreksi;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artolanggeng.purnamakertasindo.R;
import com.artolanggeng.purnamakertasindo.common.GlobalTimbang;
import com.artolanggeng.purnamakertasindo.common.ScanQR;
import com.artolanggeng.purnamakertasindo.popup.InputPemasok;
import com.artolanggeng.purnamakertasindo.popup.KoreksiPemasok;
import com.artolanggeng.purnamakertasindo.service.FragJualLife;
import com.artolanggeng.purnamakertasindo.timbangbesar.FormBesar;
import com.artolanggeng.purnamakertasindo.utils.Fungsi;
import com.artolanggeng.purnamakertasindo.utils.Preference;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragQRKoreksi extends Fragment implements FragJualLife
{
    Unbinder unbinder;

    public fragQRKoreksi()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.lay_fragarrived, container, false);

        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnScanQR, R.id.ivScanQR, R.id.tvInputPelanggan})
    public void onViewClicked(View view)
    {
        switch(view.getId())
        {
            case R.id.ivScanQR:
            case R.id.btnScanQR:
                Intent ProgresIntent = new Intent(getActivity(), ScanQR.class);
                ProgresIntent.putExtra("KodeTimbangan", "4");
                startActivity(ProgresIntent);
            break;
            case R.id.tvInputPelanggan:
                Fungsi.storeToSharedPref(getContext(), 0, Preference.PrefInputNomorPelanggan);
                KoreksiPemasok koreksiPemasok = new KoreksiPemasok(getActivity());
                koreksiPemasok.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                koreksiPemasok.show();
                koreksiPemasok.setOnDismissListener(new DialogInterface.OnDismissListener()
                {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface)
                    {
                        if(Fungsi.getIntFromSharedPref(getContext(), Preference.PrefInputNomorPelanggan) == 1)
                        {
                            GlobalTimbang globalTimbang=new GlobalTimbang(getContext(), getActivity());
                            globalTimbang.ProsesPemasokManual(0, "", "", FormKoreksi.class);
                        }
                    }
                });
            break;
        }
    }

    @Override
    public void onResumeJualLife()
    {

    }
}
