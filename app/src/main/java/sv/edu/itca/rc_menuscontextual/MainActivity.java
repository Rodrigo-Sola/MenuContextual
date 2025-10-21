package sv.edu.itca.rc_menuscontextual;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView lblMensaje;
    private ListView lvlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        lblMensaje  = findViewById(R.id.lblmensaje);


        lvlista = findViewById(R.id.lista);


        String[] datos = new String[]{
                "Elemento 1",
                "Elemento 2",
                "Elemento 3",
                "Elemento 4",
                "Elemento 5",
                "Elemento 6",
                "Elemento 7",
                "Elemento 8",
                "Elemento 9",
                "Elemento 10"
        };
        ArrayAdapter<String> adaptador = new  ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);
        lvlista.setAdapter(adaptador);

        registerForContextMenu(lblMensaje);
        registerForContextMenu(lvlista);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
        
        if(v.getId() == R.id.lblmensaje){

            inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
        } else if (v.getId() == R.id.lista) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(lvlista.getAdapter().getItem(info.position).toString());
            inflater.inflate(R.menu.menu_ctx_lista, menu);

            
        }


    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.CtxLblOpc1) {
            lblMensaje.setText(getString(R.string.lblmensjae) + getString(R.string.opc_etiqueta1));
            return true;

        } else if (id == R.id.CtxLblOpc2) {
            lblMensaje.setText(getString(R.string.lblmensjae) + getString(R.string.opc_etiqueta2));
            return true;
        }
        else if (id == R.id.CtxLblOpc1) {
            lblMensaje.setText(getString(R.string.lblmensjae) + getString(R.string.opc_lista1));
            return true;

        } else if (id == R.id.CtxLblOpc2) {
            lblMensaje.setText(getString(R.string.lblmensjae) + getString(R.string.opc_lista2));
            return true;
        }

        return super.onContextItemSelected(item);
    }





}