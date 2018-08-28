package a.agendamento.agendacontato.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import a.agendamento.agendacontato.DAO.ContatoDAO;
import a.agendamento.agendacontato.R;
import a.agendamento.agendacontato.modelo.Contato;

public class Lista_Contatos extends AppCompatActivity {
    private ListView listaContatos;
    private List<Contato> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__contatos);

        ContatoDAO dao = new ContatoDAO(this);
        contatos = dao.getLista();
        dao.close();

        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        listaContatos = (ListView) findViewById(R.id.lista_contatos);
        listaContatos.setAdapter(adapter);

        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Lista_Contatos.this, "Item selecionado: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        listaContatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Lista_Contatos.this, "Clique longo: " + position, Toast.LENGTH_LONG).show();
                return false;
            }
        });

        Button botaoAdiciona = (Button) findViewById(R.id.lista_contatos_floating_button);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lista_Contatos.this, Cadastro_Contato.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    private void carregaLista(){
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.getLista();
        dao.close();

        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);
        this.listaContatos.setAdapter(adapter);
    }
}
