package com.example.khour.zk_a1_p11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView outputTextView;
    private EditText messageTextView;
    private SeekBar privateKeySeekBar;
    private RadioButton encryptRadioButton;
    private TextView privateKeyTextView;
    private RadioGroup encryptDecryptradioGroup;
    private RadioButton decryptRadioButton;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputTextView = findViewById(R.id.outputTextView);
        messageTextView = findViewById(R.id.messageEditText);
        privateKeySeekBar = findViewById(R.id.privateKeySeekBar);
        encryptRadioButton = findViewById(R.id.encryptRadioButton);
        decryptRadioButton = findViewById(R.id.decryptRadioButton);
        privateKeyTextView = findViewById(R.id.privateKeyTextView);
        submit = findViewById(R.id.submit);
        encryptDecryptradioGroup = findViewById(R.id.encryptDecryptradioGroup);

        // listen for seek bar and update the private key text view to reflect the progress
        privateKeySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                privateKeyTextView.setText("Private Key: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // change the button text to "Encrypt" or "Decrypt" depending on which radio button is checked
        encryptDecryptradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.encryptRadioButton){
                    submit.setText("Encrypt");
                }
                else if(checkedId == R.id.decryptRadioButton){
                    submit.setText("Decrypt");
                }
            }
        });
    }

    /*
    this method is called when the user clicks on the button
     */
    public void submit(View view){

        String message = messageTextView.getText().toString();
        int key = privateKeySeekBar.getProgress();

        // encrypt the message
        if (encryptRadioButton.isChecked()){
            String encryptedMessage = CaesarCipher.encrypt(message, key);
            outputTextView.setText(encryptedMessage);
        }
        // decrypt the message
        else {
            String decryptedMessage = CaesarCipher.decrypt(message, key);
            outputTextView.setText(decryptedMessage);
        }

    }
}
