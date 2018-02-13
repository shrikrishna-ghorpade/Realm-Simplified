package com.realmsimplified;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.realmsimplified.entities.SampleEntity;
import com.realmsimplified.repos.SampleEntityRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Krishna on 2/10/2018.
 */

public class MainActivity extends AppCompatActivity {

    private ListAdapter adapter = null;
    private SampleEntityRepo sampleEntityRepo;

    private Button btnSave;
    private Button btnClear;

    private EditText edtId;
    private EditText edtName;
    private EditText edtAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doRegularStuff();

        sampleEntityRepo = new SampleEntityRepo(this, SampleEntity.class);
        setData(sampleEntityRepo.findAll());
    }

    private void doRegularStuff() {


        btnSave = (Button) findViewById(R.id.btn_save);
        btnClear = (Button) findViewById(R.id.btn_clear);

        edtId = (EditText) findViewById(R.id.edt_id);
        edtAge = (EditText) findViewById(R.id.edt_age);
        edtName = (EditText) findViewById(R.id.edt_name);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SampleEntity sampleEntity = new SampleEntity();
                if(edtId.getText() == null || edtId.getText().toString().equals("")){
                    if(edtName.getText().toString().equals("") || edtName.getText().toString() == null || edtAge.getText().toString().equals("")|| edtAge.getText().toString() == null){
                        return;
                    }
                    sampleEntity.setId(UUID.randomUUID().toString());
                    sampleEntity.setName(edtName.getText().toString().trim());
                    sampleEntity.setAge(Integer.parseInt(edtAge.getText().toString().trim()));
                    sampleEntityRepo.save(sampleEntity);

                } else {
                    sampleEntity.setId(edtId.getText().toString().trim());
                    sampleEntity.setName(edtName.getText().toString().trim());
                    sampleEntity.setAge(Integer.parseInt(edtAge.getText().toString().trim()));
                    sampleEntityRepo.saveOrUpdate(sampleEntity);
                }

                btnClear.callOnClick();
                setData(sampleEntityRepo.findAll());
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtAge.setText("");
                edtName.setText("");
                edtId.setText("");
                btnSave.setText("Save");
            }
        });
    }

    private void setData(final List<SampleEntity> sampleEntities) {

        final List<SampleEntity> sampleEntities1 = new ArrayList<>();

        sampleEntities1.addAll(sampleEntities);
        Collections.reverse(sampleEntities1);
        adapter = new ListAdapter(this, sampleEntities1);
        ListView lv = (ListView) findViewById( R.id.list_item);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("dsd","dsd "+position);

                edtId.setText(sampleEntities1.get(position).getId());
                edtName.setText(sampleEntities1.get(position).getName());
                edtAge.setText(sampleEntities1.get(position).getAge()+"");
                btnSave.setText("Update");
            }
        });
    }
}
