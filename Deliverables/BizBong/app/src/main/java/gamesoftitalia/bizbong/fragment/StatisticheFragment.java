package gamesoftitalia.bizbong.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import gamesoftitalia.bizbong.R;
import gamesoftitalia.bizbong.adapters.CustomThemePagerAdapter;
import gamesoftitalia.bizbong.entity.Impostazioni;
import gamesoftitalia.bizbong.entity.Profilo;
import gamesoftitalia.bizbong.entity.Statistiche;
import gamesoftitalia.bizbong.pie_chart.Attributi_grafico;
import gamesoftitalia.bizbong.pie_chart.Prop_grafico;

/**
 * Created by Raffaella on 19/12/2016.
 */

public class StatisticheFragment extends android.support.v4.app.Fragment{
    private static View view;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Button sfida;
    private Button sudoBizBong;
    private boolean fgrafico1=false;
    private boolean fgrafico2=false;
    private boolean fgrafico3=false;
    private boolean fgrafico4=false;
    private Impostazioni entity;

    private int [] imageProfile;
    private String[] percentuali;
    private ViewPager  viewPagerLegenda1;
    private ViewPager  viewPagerLegenda2;
    private ViewPager  viewPagerLegenda3;
    private ViewPager  viewPagerLegenda4;

    Button precedente, precedente2, precedente3, precedente4;
    Button successivo, successivo2, successivo3, successivo4;

    private List<Attributi_grafico> piedata=new ArrayList<>(0);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Context
        context = getActivity();


        // SharedPrefernces
        sharedPreferences = context.getSharedPreferences("sessioneUtente", context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        Profilo profilo = (Profilo) this.getArguments().getSerializable("profilo");
        entity = (Impostazioni) this.getArguments().getSerializable("impostazioni");

        Statistiche statistiche=profilo.getStatistiche();
        //array con i punteggi dell'utente
        final int[] punteggi=statistiche.getPunteggiList();

        view= inflater.inflate(R.layout.fragment_statistiche, container, false);

        sfida=(Button)view.findViewById(R.id.sfida);
        sudoBizBong=(Button)view.findViewById(R.id.sudobizbong);

        //controllo presenza dati da rappresentare
        if(punteggi[12]+punteggi[13]==0){
            /*messaggio impossibile fare statistiche*/
        }else {
            dati1(punteggi);
            dati2(punteggi);
        }
        sfida.setEnabled(false);

        sfida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(view.getContext(), R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                sfida.setBackgroundResource(R.drawable.xml1);
                sudoBizBong.setBackgroundResource(R.drawable.xml2);
                //oggetti da nascondere
                RelativeLayout g3=(RelativeLayout)view.findViewById(R.id.pie_container3);
                TextView tl3=(TextView)view.findViewById(R.id.legenda3);
                RelativeLayout l3=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda3);
                RelativeLayout g4=(RelativeLayout)view.findViewById(R.id.pie_container4);
                TextView tl4=(TextView)view.findViewById(R.id.legenda4);
                RelativeLayout l2=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda4);

                g3.setVisibility(View.GONE);
                tl3.setVisibility(View.GONE);
                l3.setVisibility(View.GONE);

                g4.setVisibility(View.GONE);
                tl4.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);

                //oggetti da rendere visibili
                RelativeLayout g1=(RelativeLayout)view.findViewById(R.id.pie_container1);
                RelativeLayout g2=(RelativeLayout)view.findViewById(R.id.pie_container2);
                g1.setVisibility(View.VISIBLE);
                g2.setVisibility(View.VISIBLE);

                //costruzine grafici visibii
                if(punteggi[12]+punteggi[13]==0){                       //controllo presenza dati da rappresentare
                    Toast.makeText(view.getContext(),getResources().getString(R.string.eccezione_grafico1),Toast.LENGTH_LONG).show();
                }else {
                    dati1(punteggi);
                    dati2(punteggi);
                }

                //gestione attivazione button
                sfida.setEnabled(false);
                sudoBizBong.setEnabled(true);
            }
        });

        sudoBizBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entity.getEffetti()==true) {
                    MediaPlayer mp = MediaPlayer.create(view.getContext(), R.raw.bottoni);
                    mp.start();
                }
                if(entity.getVibrazione()){
                    Vibrator g = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    g.vibrate(100);
                }
                sudoBizBong.setBackgroundResource(R.drawable.xml1);
                sfida.setBackgroundResource(R.drawable.xml2);

                //oggetti da nascondere
                RelativeLayout g1=(RelativeLayout)view.findViewById(R.id.pie_container1);
                TextView tl1=(TextView)view.findViewById(R.id.legenda1);
                RelativeLayout l1=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda1);
                RelativeLayout g2=(RelativeLayout)view.findViewById(R.id.pie_container2);
                TextView tl2=(TextView)view.findViewById(R.id.legenda2);
                RelativeLayout l2=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda2);

                g1.setVisibility(View.GONE);
                tl1.setVisibility(View.GONE);
                l1.setVisibility(View.GONE);

                g2.setVisibility(View.GONE);
                tl2.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);

                //oggetti da rendere visibili
                RelativeLayout g3=(RelativeLayout)view.findViewById(R.id.pie_container3);
                RelativeLayout g4=(RelativeLayout)view.findViewById(R.id.pie_container4);
                g3.setVisibility(View.VISIBLE);
                g4.setVisibility(View.VISIBLE);


                //costruzine grafici visibii
                //controllo presenza dati da rappresentare tutte e due i grafici
                if (punteggi[14]+punteggi[15]+punteggi[16]==0&&punteggi[17]+punteggi[18]+punteggi[19]==0){
                                    Toast.makeText(view.getContext(),R.string.eccezione_grafico4,Toast.LENGTH_LONG).show();
                    //controllo presenza dati da rappresentare grafico 1
                }else if (punteggi[14]+punteggi[15]+punteggi[16]!=0&&punteggi[17]+punteggi[18]+punteggi[19]==0) {
                                                                dati3(punteggi);
                                                                Toast.makeText(view.getContext(),R.string.eccezione_grafico3,Toast.LENGTH_LONG).show();
                      }
                    //controllo presenza dati da rappresentare  grafico 2
                    else if (punteggi[17]+punteggi[18]+punteggi[19]!=0&&punteggi[14]+punteggi[15]+punteggi[16]==0) {
                                                           dati4(punteggi);
                                                           Toast.makeText(view.getContext(),R.string.eccezione_grafico2,Toast.LENGTH_LONG).show();
                    }
                          else {
                              dati3(punteggi);
                              dati4(punteggi);
                          }

                //gestione attivazione button
                sfida.setEnabled(true);
                sudoBizBong.setEnabled(false);


            }
        });
        return view;
    }

    private void dati1(int []punteggi){
        //punteggio totale dell utente sfide
        double totale=(double)punteggi[12]+(double)punteggi[13];

        //punteggio delle singole materie
        double sto=(double)punteggi[0];
        double geo=(double)punteggi[1];
        double spo=(double)punteggi[2];
        double sci=(double)punteggi[3];
        double cin=(double)punteggi[4];
        double mat=(double)punteggi[5];
        double log=(double)punteggi[6];
        double vid=(double)punteggi[7];
        double inf=(double)punteggi[8];
        double car=(double)punteggi[9];
        double art=(double)punteggi[10];
        double mus=(double)punteggi[11];

         /*calcolo percentuale  e arrotondamento a due cifre decimali*/
        sto=arrotondaPerEccesso_maxcifre(percentuale(sto,totale),2);
        geo=arrotondaPerEccesso_maxcifre(percentuale(geo,totale),2);
        spo=arrotondaPerEccesso_maxcifre(percentuale(spo,totale),2);
        sci=arrotondaPerEccesso_maxcifre(percentuale(sci,totale),2);
        cin=arrotondaPerEccesso_maxcifre(percentuale(cin,totale),2);
        mat=arrotondaPerEccesso_maxcifre(percentuale(mat,totale),2);
        log=arrotondaPerEccesso_maxcifre(percentuale(log,totale),2);
        vid=arrotondaPerEccesso_maxcifre(percentuale(vid,totale),2);
        inf=arrotondaPerEccesso_maxcifre(percentuale(inf,totale),2);
        car=arrotondaPerEccesso_maxcifre(percentuale(car,totale),2);
        art=arrotondaPerEccesso_maxcifre(percentuale(art,totale),2);
        mus=arrotondaPerEccesso_maxcifre(percentuale(mus,totale),2);

        /*conversione dei double in integer per grafico */
        int storia=(int)sto;
        int geografia=(int)geo;
        int sport=(int)spo;
        int scienze=(int)sci;
        int cinema=(int)cin;
        int matematica=(int)mat;
        int logica=(int)log;
        int videogames=(int)vid;
        int informatica=(int)inf;
        int cartoni=(int)car;
        int arte=(int)art;
        int musica=(int)mus;

        if (fgrafico1==false) {
            costruzioneGrafico1(storia, geografia, sport, scienze, cinema, matematica,
                                logica, videogames, informatica, cartoni, arte, musica);
            fgrafico1=true;
        }
        costruzioneLegenda1(sto, geo, spo, sci, cin, mat, log, vid, inf, car, art, mus);
    }


    private void costruzioneGrafico1(int storia, int geografia, int sport, int scienze, int cinema, int matematica,
                                     int logica, int videogames, int informatica, int cartoni, int arte, int musica){
        Attributi_grafico item;
        int maxCount=0;
        int itemCount=0;
        int items[]={storia,geografia,sport,scienze,cinema,matematica,
                     logica,videogames,informatica,cartoni,arte,musica};
        int colors[]={Color.parseColor("#EB327C"),Color.parseColor("#347C1A"),Color.parseColor("#BF8AC2"),Color.parseColor("#C3FFA1"), Color.parseColor("#FF1300"), Color.parseColor("#FFF910"),
                      Color.parseColor("#7F0037"),Color.parseColor("#54FF00"),Color.parseColor("#00FFFF"),Color.parseColor("#49C9A4"),Color.parseColor("#0094FF"),Color.parseColor("#D78C46")};
        for(int i=0;i<items.length;i++) {
            itemCount=items[i];
            item=new Attributi_grafico();
            item.setCount(itemCount);
            item.setColor(colors[i]);
            piedata.add(item);
            maxCount=maxCount+itemCount;
        }
        int size=600;
        int BgColor=Color.alpha(0);
        Bitmap mBaggroundImage=Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_4444);
        Prop_grafico piechart=new Prop_grafico(context);
        piechart.setLayoutParams(new ViewGroup.LayoutParams(size,size));
        piechart.setGeometry(size, size, 10, 10, 10, 10,10);
        piechart.setSkinparams(BgColor);
        piechart.setData(piedata, maxCount);
        piechart.invalidate();
        piechart.draw(new Canvas(mBaggroundImage));
        ImageView mImageView=new ImageView(context);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mImageView.setBackgroundColor(BgColor);
        mImageView.setImageBitmap(mBaggroundImage);
        RelativeLayout finalLayout=(RelativeLayout) view.findViewById(R.id.pie_container1);
        finalLayout.addView(mImageView);
    }

    private void costruzioneLegenda1(double sto,double geo,double spo,double sci,double cin,double mat,
                                     double log,double vid,double inf,double car,double art,double mus){
        TextView textView=(TextView)view.findViewById(R.id.legenda1);
        textView.setVisibility(View.VISIBLE);
        RelativeLayout l=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda1);
        l.setVisibility(View.VISIBLE);

        //Array
        if (entity.getLingua().equals("ita"))
                   imageProfile = new int[]{R.drawable.img_sto_ita, R.drawable.img_geo_ita, R.drawable.img_spo_ita, R.drawable.img_sci_ita, R.drawable.img_cin_ita, R.drawable.img_mat_ita,
                                            R.drawable.img_log_ita, R.drawable.img_vid_ita, R.drawable.img_inf_ita, R.drawable.img_car_ita, R.drawable.img_art_ita, R.drawable.img_mus_ita,};
            else if (entity.getLingua().equals("eng"))
                                imageProfile = new int[]{R.drawable.img_sto_eng, R.drawable.img_geo_eng, R.drawable.img_spo_eng, R.drawable.img_sci_eng, R.drawable.img_cin_eng, R.drawable.img_mat_eng,
                                                         R.drawable.img_log_eng, R.drawable.img_vid_eng, R.drawable.img_inf_eng, R.drawable.img_car_eng, R.drawable.img_art_eng, R.drawable.img_mus_eng,};
                 else if (entity.getLingua().equals("ukr"))
                                        imageProfile = new int[]{R.drawable.img_sto_ukr, R.drawable.img_geo_ukr, R.drawable.img_spo_ukr, R.drawable.img_sci_ukr, R.drawable.img_cin_ukr, R.drawable.img_mat_ukr,
                                                                 R.drawable.img_log_ukr, R.drawable.img_vid_ukr, R.drawable.img_inf_ukr, R.drawable.img_car_ukr, R.drawable.img_art_ukr, R.drawable.img_mus_ukr,};
                       else
                            imageProfile = new int[]{R.drawable.img_sto_ita, R.drawable.img_geo_ita, R.drawable.img_spo_ita, R.drawable.img_sci_ita, R.drawable.img_cin_ita, R.drawable.img_mat_ita,
                                                     R.drawable.img_log_ita, R.drawable.img_vid_ita, R.drawable.img_inf_ita, R.drawable.img_car_ita, R.drawable.img_art_ita, R.drawable.img_mus_ita,};

        percentuali =new String[]{""+sto+"%",""+geo+"%",""+spo+"%",""+sci+"%",""+cin+"%",""+mat+"%",
                                  ""+log+"%",""+vid+"%",""+inf+"%",""+car+"%",""+art+"%",""+mus+"%",};

        // ViewPager
        viewPagerLegenda1 = (ViewPager) view.findViewById(R.id.viewPagerLegenda1);
        viewPagerLegenda1.setAdapter(new CustomThemePagerAdapter(context, imageProfile, percentuali));

        //Precedente Successivo
        precedente = (Button) view.findViewById(R.id.precedente);
        precedente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda1.getCurrentItem() == 0)
                    viewPagerLegenda1.setCurrentItem(11, true);
                else
                    viewPagerLegenda1.setCurrentItem(viewPagerLegenda1.getCurrentItem() - 1, true);
            }
        });
        successivo = (Button) view.findViewById(R.id.successivo);
        successivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda1.getCurrentItem() == 11)
                    viewPagerLegenda1.setCurrentItem(0, true);
                else
                    viewPagerLegenda1.setCurrentItem(viewPagerLegenda1.getCurrentItem() + 1, true);
            }
        });
    }

    private void dati2(int punteggi[]){
        //punteggio totale dell utente sfida
        double totale=(double)punteggi[12]+(double)punteggi[13];

        //punteggi modalità
        double cla=(double)punteggi[12];
        double bb=(double)punteggi[13];

        /*calcolo percentuale  e arrotondamento a due cifre decimali*/
        cla=arrotondaPerEccesso_maxcifre(percentuale(cla,totale),2);
        bb=arrotondaPerEccesso_maxcifre(percentuale(bb,totale),2);

         /*conversione dei double in integer per grafico */
        int classica=(int)cla;
        int bizbong=(int)bb;

        if (fgrafico2==false) {
            costruzioneGrafico2(classica, bizbong);
            fgrafico2=true;
        }
        costruzioneLegenda2(cla, bb);
    }


    private void costruzioneGrafico2(int classica, int bizbong){
        Attributi_grafico item;
        int maxCount=0;
        int itemCount=0;
        int items[]={classica,bizbong};
        int colors[]={Color.parseColor("#8CC3FF"),Color.parseColor("#FF3111")};
        for(int i=0;i<items.length;i++) {
            itemCount=items[i];
            item=new Attributi_grafico();
            item.setCount(itemCount);
            item.setColor(colors[i]);
            piedata.add(item);
            maxCount=maxCount+itemCount;
        }
        int size=600;
        int BgColor=Color.alpha(0);
        Bitmap mBaggroundImage=Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_4444);
        Prop_grafico piechart=new Prop_grafico(context);
        piechart.setLayoutParams(new ViewGroup.LayoutParams(size,size));
        piechart.setGeometry(size, size, 10, 10, 10, 10,10);
        piechart.setSkinparams(BgColor);
        piechart.setData(piedata, maxCount);
        piechart.invalidate();
        piechart.draw(new Canvas(mBaggroundImage));
        ImageView mImageView=new ImageView(context);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mImageView.setBackgroundColor(BgColor);
        mImageView.setImageBitmap(mBaggroundImage);
        RelativeLayout finalLayout=(RelativeLayout)view.findViewById(R.id.pie_container2);
        finalLayout.addView(mImageView);
    }

    private void costruzioneLegenda2(double cla,double bb){
        //rendere layout leggenda visibile impostare legenda su visibile gone tutte
        TextView textView=(TextView)view.findViewById(R.id.legenda2);
        textView.setVisibility(View.VISIBLE);
        RelativeLayout l=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda2);
        l.setVisibility(View.VISIBLE);

        //Array
        if (entity.getLingua().equals("ita"))
                    imageProfile = new int[]{R.drawable.img_cc_ita, R.drawable.img_bb_ita};
        /*else if (entity.getLingua().equals("eng"))
                            imageProfile = new int[]{R.drawable.img_cc_eng, R.drawable.img_bb_eng};
        else if (entity.getLingua().equals("ukr"))
                                imageProfile = new int[]{R.drawable.img_cc_ukr, R.drawable.img_bb_ukr};*/
        else
            imageProfile = new int[]{R.drawable.img_cc_ita, R.drawable.img_bb_ita};

        percentuali =new String[]{""+cla+"%",""+bb+"%"};

        // ViewPager
        viewPagerLegenda2 = (ViewPager) view.findViewById(R.id.viewPagerLegenda2);
        viewPagerLegenda2.setAdapter(new CustomThemePagerAdapter(context, imageProfile, percentuali));

        //Precedente Successivo
        precedente2 = (Button) view.findViewById(R.id.precedente2);
        precedente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda2.getCurrentItem() == 0)
                    viewPagerLegenda2.setCurrentItem(1, true);
                else
                    viewPagerLegenda2.setCurrentItem(viewPagerLegenda2.getCurrentItem() - 1, true);
            }
        });
        successivo2 = (Button) view.findViewById(R.id.successivo2);
        successivo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda2.getCurrentItem() == 1)
                    viewPagerLegenda2.setCurrentItem(0, true);
                else
                    viewPagerLegenda2.setCurrentItem(viewPagerLegenda2.getCurrentItem() + 1, true);
            }
        });
    }


    private void dati3(int punteggi[]){
        //punteggio totale dell utente
        double totale=(double)punteggi[14]+(double)punteggi[15]+(double)punteggi[16];

        //punteggi modalità
        double f=(double)punteggi[14];
        double m=(double)punteggi[15];
        double d=(double)punteggi[16];

        /*calcolo percentuale  e arrotondamento a due cifre decimali*/
        f=arrotondaPerEccesso_maxcifre(percentuale(f,totale),2);
        m=arrotondaPerEccesso_maxcifre(percentuale(m,totale),2);
        d=arrotondaPerEccesso_maxcifre(percentuale(d,totale),2);

         /*conversione dei double in integer per grafico */
        int facile=(int)f;
        int medio=(int)m;
        int difficile=(int)d;

        if (fgrafico3==false) {
            costruzioneGrafico3(facile, medio, difficile);
            fgrafico3=true;
        }
        costruzioneLegenda3(f, m, d);
    }


    private void costruzioneGrafico3(int facile, int medio,int difficile){
        Attributi_grafico item;
        int maxCount=0;
        int itemCount=0;
        int items[]={facile,medio,difficile};
        int colors[]={Color.parseColor("#0094FF"),Color.parseColor("#87FF93"),Color.parseColor("#FF9366")};
        for(int i=0;i<items.length;i++) {
            itemCount=items[i];
            item=new Attributi_grafico();
            item.setCount(itemCount);
            item.setColor(colors[i]);
            piedata.add(item);
            maxCount=maxCount+itemCount;
        }
        int size=600;
        int BgColor=Color.alpha(0);
        Bitmap mBaggroundImage=Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_4444);
        Prop_grafico piechart=new Prop_grafico(context);
        piechart.setLayoutParams(new ViewGroup.LayoutParams(size,size));
        piechart.setGeometry(size, size, 10, 10, 10, 10,10);
        piechart.setSkinparams(BgColor);
        piechart.setData(piedata, maxCount);
        piechart.invalidate();
        piechart.draw(new Canvas(mBaggroundImage));
        ImageView mImageView=new ImageView(context);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mImageView.setBackgroundColor(BgColor);
        mImageView.setImageBitmap(mBaggroundImage);
        RelativeLayout finalLayout=(RelativeLayout)view.findViewById(R.id.pie_container3);
        finalLayout.addView(mImageView);
    }

    private void costruzioneLegenda3(double f,double m,double d){
        TextView textView=(TextView)view.findViewById(R.id.legenda3);
        textView.setVisibility(View.VISIBLE);
        RelativeLayout l=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda3);
        l.setVisibility(View.VISIBLE);

        //Array
        if (entity.getLingua().equals("ita"))
            imageProfile = new int[]{R.drawable.img_su2x2f_ita, R.drawable.img_su2x2m_ita, R.drawable.img_su2x2d_ita};
        /*else if (entity.getLingua().equals("eng"))
                             imageProfile = new int[]{R.drawable.img_su2x2f_eng, R.drawable.img_su2x2m_eng, R.drawable.img_su2x2d_eng};
        else if (entity.getLingua().equals("ukr"))
                                 imageProfile = new int[]{R.drawable.img_su2x2f_ukr, R.drawable.img_su2x2m_ukr, R.drawable.img_su2x2d_ukr};*/
        else
            imageProfile = new int[]{R.drawable.img_su2x2f_ita, R.drawable.img_su2x2m_ita, R.drawable.img_su2x2d_ita};

        percentuali =new String[]{""+f+"%",""+m+"%",""+d+"%"};

        // ViewPager
        viewPagerLegenda3 = (ViewPager) view.findViewById(R.id.viewPagerLegenda3);
        viewPagerLegenda3.setAdapter(new CustomThemePagerAdapter(context, imageProfile, percentuali));

        //Precedente Successivo
        precedente3 = (Button) view.findViewById(R.id.precedente3);
        precedente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda3.getCurrentItem() == 0)
                    viewPagerLegenda3.setCurrentItem(2, true);
                else
                    viewPagerLegenda3.setCurrentItem(viewPagerLegenda3.getCurrentItem() - 1, true);
            }
        });
        successivo3 = (Button) view.findViewById(R.id.successivo3);
        successivo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda3.getCurrentItem() == 2)
                    viewPagerLegenda3.setCurrentItem(0, true);
                else
                    viewPagerLegenda3.setCurrentItem(viewPagerLegenda3.getCurrentItem() + 1, true);
            }
        });
    }



    private void dati4(int punteggi[]){
        //punteggio totale dell utente
        double totale=(double)punteggi[17]+(double)punteggi[18]+(double)punteggi[19];

        //punteggi modalità
        double f=(double)punteggi[17];
        double m=(double)punteggi[18];
        double d=(double)punteggi[19];

        /*calcolo percentuale  e arrotondamento a due cifre decimali*/
        f=arrotondaPerEccesso_maxcifre(percentuale(f,totale),2);
        m=arrotondaPerEccesso_maxcifre(percentuale(m,totale),2);
        d=arrotondaPerEccesso_maxcifre(percentuale(d,totale),2);

         /*conversione dei double in integer per grafico */
        int facile=(int)f;
        int medio=(int)m;
        int difficile=(int)d;

        if (fgrafico4==false) {
            costruzioneGrafico4(facile, medio, difficile);
            fgrafico4=true;
        }
        costruzioneLegenda4(f, m, d);
    }


    private void costruzioneGrafico4(int facile, int medio,int difficile){
        Attributi_grafico item;
        int maxCount=0;
        int itemCount=0;
        int items[]={facile,medio,difficile};
        int colors[]={Color.parseColor("#FF8142"),Color.parseColor("#5FFF47"),Color.parseColor("#BF72FF")};
        for(int i=0;i<items.length;i++) {
            itemCount=items[i];
            item=new Attributi_grafico();
            item.setCount(itemCount);
            item.setColor(colors[i]);
            piedata.add(item);
            maxCount=maxCount+itemCount;
        }
        int size=600;
        int BgColor=Color.alpha(0);
        Bitmap mBaggroundImage=Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_4444);
        Prop_grafico piechart=new Prop_grafico(context);
        piechart.setLayoutParams(new ViewGroup.LayoutParams(size,size));
        piechart.setGeometry(size, size, 10, 10, 10, 10,10);
        piechart.setSkinparams(BgColor);
        piechart.setData(piedata, maxCount);
        piechart.invalidate();
        piechart.draw(new Canvas(mBaggroundImage));
        ImageView mImageView=new ImageView(context);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mImageView.setBackgroundColor(BgColor);
        mImageView.setImageBitmap(mBaggroundImage);
        RelativeLayout finalLayout=(RelativeLayout)view.findViewById(R.id.pie_container4);
        finalLayout.addView(mImageView);
    }

    private void costruzioneLegenda4(double f,double m,double d){
        TextView textView=(TextView)view.findViewById(R.id.legenda4);
        textView.setVisibility(View.VISIBLE);
        RelativeLayout l=(RelativeLayout)view.findViewById(R.id.visualizza_leggenda4);
        l.setVisibility(View.VISIBLE);

        //Array
        if (entity.getLingua().equals("ita"))
            imageProfile = new int[]{R.drawable.img_su3x3f_ita, R.drawable.img_su2x2m_ita, R.drawable.img_su2x2d_ita};
        /*else if (entity.getLingua().equals("eng"))
                             imageProfile = new int[]{R.drawable.img_su3x3f_eng, R.drawable.img_su3x3m_eng, R.drawable.img_su3x3d_eng};
        else if (entity.getLingua().equals("ukr"))
                                 imageProfile = new int[]{R.drawable.img_su3x3f_ukr, R.drawable.img_su3x3m_ukr, R.drawable.img_su3x3d_ukr};*/
        else
            imageProfile = new int[]{R.drawable.img_su2x2f_ita, R.drawable.img_su2x2m_ita, R.drawable.img_su2x2d_ita};

        percentuali =new String[]{""+f+"%",""+m+"%",""+d+"%"};

        // ViewPager
        viewPagerLegenda4 = (ViewPager) view.findViewById(R.id.viewPagerLegenda4);
        viewPagerLegenda4.setAdapter(new CustomThemePagerAdapter(context, imageProfile, percentuali));

        //Precedente Successivo
        precedente4 = (Button) view.findViewById(R.id.precedente4);
        precedente4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda4.getCurrentItem() == 0)
                    viewPagerLegenda4.setCurrentItem(2, true);
                else
                    viewPagerLegenda4.setCurrentItem(viewPagerLegenda4.getCurrentItem() - 1, true);
            }
        });
        successivo4 = (Button) view.findViewById(R.id.successivo4);
        successivo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPagerLegenda4.getCurrentItem() == 2)
                    viewPagerLegenda4.setCurrentItem(0, true);
                else
                    viewPagerLegenda4.setCurrentItem(viewPagerLegenda4.getCurrentItem() + 1, true);
            }
        });
    }

    private double percentuale(double x,double t){
        double risultato=(x*100)/t;
        return risultato;
    }

    private double arrotondaPerEccesso_maxcifre(final double valore, final int cifreDecimali) {
        final double potenza = Math.pow(10, cifreDecimali);
        return Math.ceil(valore * potenza) / potenza;
    }
}
