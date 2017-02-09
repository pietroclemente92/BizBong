package gamesoftitalia.bizbong.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import gamesoftitalia.bizbong.R;

/**
 * Created by Raffaella on 08/01/2017.
 */

public class ClassificaAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final int[] punteggioClassificato;
    private final String[] nicknameClassificato, iconaProfiloClassificato;

    public ClassificaAdapter(Context context, String[] iconaProfiloClassificato, String[] nicknameClassificato, int[] punteggioClassificato) {
        super(context, R.layout.layout_classifica_list, nicknameClassificato);
        this.context = context;
        this.iconaProfiloClassificato = iconaProfiloClassificato;
        this.punteggioClassificato = punteggioClassificato;
        this.nicknameClassificato = nicknameClassificato;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.layout_classifica_list, parent, false);
        TextView posizioneView = (TextView) rowView.findViewById(R.id.classifica_posizione);
        posizioneView.setText(String.valueOf(position+1));
        ImageView iconaProfiloImageView = (ImageView) rowView.findViewById(R.id.classifica_icona_profilo);
        TextView nicknameView = (TextView) rowView.findViewById(R.id.classifica_nickname);
        nicknameView.setText(nicknameClassificato[position]);
        TextView punteggioView = (TextView) rowView.findViewById(R.id.classifica_punteggio);
        punteggioView.setText(String.valueOf(punteggioClassificato[position]));

        // Change icon based on name
        iconaProfiloImageView.getResources().getIdentifier(iconaProfiloClassificato[position], null, context.getPackageName());

        return rowView;
    }
}