package susparaconcursosfree.mxtechnologies.com.susparaconcursosfree;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Moisés on 21/04/2016.
 */
public class CompleteVersion extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completeversion, container, false);
        ImageButton imageButton5 = (ImageButton)view.findViewById(R.id.imageButton5);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=susparaconcursos.mxtechnologies.com.susparaconcursos");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        return view;
    }
}