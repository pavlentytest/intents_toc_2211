package ru.samsung.itschool.mdev.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn, btn2;
    public static final String VALUE = "key";
    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == MainActivity2.CODE) {
                    String str = result.getData().getStringExtra(MainActivity2.KEY2);
                    Toast.makeText(getApplicationContext(),
                            str,
                            Toast.LENGTH_LONG).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // намерение - явное
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra(VALUE,getString(R.string.hello));
              //  startActivity(intent); // я не жду обратные данные
                launcher.launch(intent); // жду обратные данные
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // намерение - неявное
              /*  Intent intent = new Intent(Intent.ACTION_VIEW);
                String url = "https://samsung.com";
                intent.setData(Uri.parse(url));
                startActivity(intent);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String phone = "tel:123456";
                intent.setData(Uri.parse(phone));
                startActivity(intent);*/

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

    }
}