package xyz.yunikitin.arithmeticgame;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button playButton, recordButton, exitButton;
    private String[] level = {"Easy", "Medium", "Hard"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(this);
        recordButton = (Button) findViewById(R.id.high_button);
        recordButton.setOnClickListener(this);
        exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_button:
                //playActivity run
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose a level").setSingleChoiceItems(level,
                        0, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startPlay(which);
                            }
                        });
                AlertDialog ad = builder.create();
                ad.show();
                break;
            case R.id.high_button:
                //recordsActivity run
                Intent highIntent = new Intent(this, RecordsActivity.class);
                this.startActivity(highIntent);
                break;
            case R.id.exit_button:
                //exit program
               openQuitDialog();
                break;
        }
    }

    private void startPlay(int chosenLevel) {
        Intent playIntent = new Intent(this, PlayActivity.class);
        playIntent.putExtra("level", chosenLevel);
        this.startActivity(playIntent);
    }

    @Override
    public void onBackPressed() {
        openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainActivity.this);
        quitDialog.setTitle("Exit: Are you sure?");

        quitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        quitDialog.show();
    }
}
