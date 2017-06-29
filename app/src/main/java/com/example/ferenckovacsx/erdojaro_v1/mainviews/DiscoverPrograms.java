package com.example.ferenckovacsx.erdojaro_v1.mainviews;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ferenckovacsx.erdojaro_v1.javabeans.Program;
import com.example.ferenckovacsx.erdojaro_v1.adapters.ProgramPagerAdapter;
import com.example.ferenckovacsx.erdojaro_v1.R;

import java.util.ArrayList;


public class DiscoverPrograms extends Fragment {

    private ViewPager viewPager;
    private static ProgramPagerAdapter adapter;
    ArrayList<Program> programList;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View programView = inflater.inflate(R.layout.fragment_discover_programs, container, false);

        programList = new ArrayList<>();
        programList.add(new Program(1, getString(R.string.program_1_name), getString(R.string.program_no_date), R.drawable.program_1, getString(R.string.program_1_description)));
        programList.add(new Program(2, getString(R.string.program_2_name), getString(R.string.program_no_date), R.drawable.program_1, getString(R.string.program_2_description)));
        programList.add(new Program(3, getString(R.string.program_3_name), getString(R.string.program_no_date), R.drawable.program_1, getString(R.string.program_3_description)));
        programList.add(new Program(4, getString(R.string.program_4_name), getString(R.string.program_no_date), R.drawable.program_1, getString(R.string.program_4_description)));
        programList.add(new Program(5, getString(R.string.program_5_name), getString(R.string.program_no_date), R.drawable.program_1, getString(R.string.program_5_description)));





//        programList.add(new Program("Különvonat Mahócára", "2017. Július 22. Szombat", R.drawable.program_2, "Az ÉSZAKERDŐ Zrt. 2017. július 22.-én szombaton ismét különvonatot indít Mahócára, a Lillafüredi Állami Erdei Vasút menetrend szerinti vonatok által amúgy nem járt szárnyvonalára. Vendégeink számára egyéni kikapcsolódási lehetőségeket kínálunk Andókút és Mahóca környékén.\n" +
//                "A program keretében délelőtt 9:30-kor,majd délután 14:20-kor különvonatok indulnak Miskolc - Dorottya utcáról Mahócára. Visszaindulás Mahócáról 12:00, 15:45 és 17:45 órakor. Szerelvényeink az időjárásnak megfelelő összeállításban, elsősorban nyitott személykocsikból fognak állni de szükség esetén néhány zárt kocsi is a vonat részét képezheti majd. Vonógépként a tervek szerint a LÁEV két C-50-es kismozdonya fog szolgálni.\n"));
//        programList.add(new Program("Lillafüredi Erdei Vasút","Állandó program", R.drawable.program_1, "A Lillafüredi Állami Edei Vasút (LÁEV) két vonallal üzemel.\n" +
//                "A fővonal a Miskolc, Dorottya úti végállomás és Garadna között 14 km hosszú, a parasznyai szárnyvonal a fővonali papírgyári elágazástól Mahócán át Taksalápa állomásig 16 km. A parasznyai szárnyvonal jelenleg csak Ortástetőig járható. A szárnyvonalon menetrend szerinti vonatok nem közlekednek."));
//        programList.add(new Program("Anna-barlang túra","Állandó program", R.drawable.poi_5, "A fokozottan védett Anna-barlang Lillafüreden, Miskolctól 3 km-re nyílik. Bejárata a Palota-szálló alatti Függőkertek aljában, közvetlenül a Szinva-vízesés mellett található. Járatainak hossza 570 m, melyből a bemutatott szakasz hossza 208 m.\n" +
//                "\n" +
//                "A barlang a Szinva forrás vizéből kivált mésztufában keletkezett. Az üregrendszer egyidős a kőzettel, melynek képződése 150.000-200.000 évvel ezelőtt kezdődött, és napjainkban is tart. Az üreg különlegessége éppen befoglaló kőzetéből és gyönyörű, egyedülálló képződményeiből adódik. Az üregrendszer kialakulása összefügg az Anna-források működésével.\n" +
//                "\n" +
//                "A barlangot 1927-ben építették ki az idegenforgalmi bemutatás céljára. A forrásokat 1953-ban bekapcsolták Miskolc város vízellátásába, ekkor a forrásokat feltáró szakaszokat lefalazták az idegenforgalmi résztől.\n" +
//                "\n" +
//                " \n" +
//                "\n" +
//                "Vezetett túrák indulnak minden nap, egész órakor, az alábbiak szeint:\n" +
//                "\n" +
//                "Nyári időszakban (március 26 – szeptember 30) naponta 10.00 – 16.00 óráig.\n" +
//                "\n" +
//                "Októberben (október 1 - október 31) naponta 10:00 - 15:00 óráig.\n" +
//                "\n" +
//                "Téli időszakban (november 1 – március 25) naponta egy garantált túra 12:00 órakor, egyéb időpontokban zárva.\n" +
//                "\n" +
//                "Minimális létszám a túraindításhoz: 4 fő. Kivétel ez alól a 12:00 órakor induló garantált túra, ez az év minden napján létszámtól függetlenül indul.\n" +
//                "\n" +
//                "Csoportok részére előre bejelentkezés szükséges: tel./fax: (46) 334-130.\n" +
//                "\n" +
//                "\n" +
//                "A túra időtartama: 25 perc\n" +
//                "A barlangi környezet, az ajánlott ruházat:\n" +
//                "\n" +
//                "Az Anna-barlang túravezetőnk kíséretében látogatható szakaszai villanyvilágítással ellátottak, utcai öltözetben bejárhatók. A közlekedést korlátok, lépcsők és egyenletes, vízmentesített sétautak segítik. A villanyvilágítás egyaránt elősegíti a biztonságos közlekedést, és a látványos barlangi képződményekben való gyönyörködést.\n" +
//                "\n" +
//                "Az Anna-barlang megközelítése\n" +
//                "\n" +
//                "Az Anna-barlang a Tiszai-pályaudvarról 1-es busszal, vagy 1-es villamossal a Diósgyőri-városközpontig, majd onnan 5-ös autóbusszal a Palotaszálló melletti, vagy a 15-ös autóbusszal a Hámori-tó melletti buszmegállóig, ahonnan kb. 200 m-es könnyű sétával érhető el a barlang.\n" +
//                "\n" +
//                "\n" +
//                "Személygépkocsival érkezőknek a Hámori-tó mellett kialakított parkolóban (a barlangtól 250 m-re) vagy a lillafüredi felső parkolóban (a barlangtól 600 m-re) érdemes parkolni."));

        Log.i("POIFragment", "megtortent");
        Log.i("POIFragment", "lista" + programList.toString());

        viewPager = (ViewPager) programView.findViewById(R.id.wiewPager);
        adapter = new ProgramPagerAdapter(programList, getActivity().getApplicationContext());

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(5);

        viewPager.setAdapter(adapter);

        return programView;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
