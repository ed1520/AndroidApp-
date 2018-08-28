package a.agendamento.agendacontato.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import a.agendamento.agendacontato.DAO.ContatoDAO;
import a.agendamento.agendacontato.Helper.CadastroHelper;
import a.agendamento.agendacontato.R;
import a.agendamento.agendacontato.modelo.Contato;

public class Cadastro_Contato extends AppCompatActivity {
    private CadastroHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        this.helper = new CadastroHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contatos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cadastro_ok:
                Contato contato = helper.pegaContatoDoCadastro();
                ContatoDAO dao = new ContatoDAO(Cadastro_Contato.this);
                if (helper.temNome()){
                    if (contato.getId() == null){
                        dao.insere(contato);
                    }else {

                    }
                    finish();
                    return true;
                }else {
                    helper.mostraErro();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
