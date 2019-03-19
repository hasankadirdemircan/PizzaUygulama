package hkadirdemircan.com.pizzauygulama.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hkadirdemircan.com.pizzauygulama.R;
import hkadirdemircan.com.pizzauygulama.model.PideKaydet;

public class AdapterSiparislerim extends BaseAdapter {

    List<PideKaydet> list;
    Context context;

    public AdapterSiparislerim(List<PideKaydet> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //layout tanimlamasi.
        convertView = LayoutInflater.from(context).inflate(R.layout.siparislerim_layout, parent, false);

        //layout'da bulunan textView'leri tanimliyoruz.
        TextView siparisPideAd = (TextView) convertView.findViewById(R.id.siparisPideAd);
        TextView pideAdetTextView = (TextView) convertView.findViewById(R.id.pideAdetTextView);
        TextView pideFiyatTextView = (TextView) convertView.findViewById(R.id.pideFiyatTextView);

        //layout'da bulunan textView'lere bilgieri set edecegiz.
        siparisPideAd.setText(list.get(position).getPideCesit());
        pideAdetTextView.setText(list.get(position).getAdet());
        pideFiyatTextView.setText(list.get(position).getTutar());

        return convertView;
    }
}
