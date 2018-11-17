package com.example.anunay.zetamaths;

import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private TextView number1,number2,operator,score,timer,st,tt,finalscore,ts2;
    private Button play,settings;
    private ConstraintLayout gamepanel,buttonpanel,scorepanel;
    private EditText answer;
    private Random random = new Random();
    private Integer ans,interval=120;
    private Handler handler =new Handler();
    private Runnable r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        number1=findViewById(R.id.number1);
        number2=findViewById(R.id.number2);
        operator=findViewById(R.id.operator);
        play=findViewById(R.id.play);
        settings=findViewById(R.id.settings);
        gamepanel=findViewById(R.id.test);
        buttonpanel=findViewById(R.id.bp);
        answer=findViewById(R.id.answer);
        score=findViewById(R.id.score);
        timer=findViewById(R.id.timer);
        st=findViewById(R.id.ts);
        tt=findViewById(R.id.tt);
        ts2=findViewById(R.id.ts2);
        finalscore=findViewById(R.id.score2);
        scorepanel=findViewById(R.id.scorepanel);
        r = new Runnable() {
            @Override
            public void run() {
                if(interval>0)
                {
                    --interval;
                    timer.setText(interval+"");
                    handler.postDelayed(this,1000);
                }
                else
                {
                    handler.removeCallbacks(this);
                    stopgame();
                }
            }
        };



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    stopgame();
                    handler.removeCallbacks(r);
                    gamepanel.setVisibility(View.VISIBLE);
                    tt.setVisibility(View.VISIBLE);
                    st.setVisibility(View.VISIBLE);
                    score.setVisibility(View.VISIBLE);
                    timer.setVisibility(View.VISIBLE);
                    scorepanel.setVisibility(View.INVISIBLE);
                    play.setText("RETRY");
                    gensetter();
                    handler.post(r);
                    answer.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                            checker();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });

                }
        });



    }

    public void gensetter()
    {
        Integer opp = random.nextInt(4);
        Integer num1=0,num2=0;
        switch(opp)
        {
            case 0:
                num1=random.nextInt(99)+2;
                num2=random.nextInt(99)+2;
                ans=num1+num2;
                number1.setText(num1+"");
                number2.setText(num2+"");
                operator.setText("+");
                break;
            case 1:
                num1=random.nextInt(99)+2;
                num2=random.nextInt(99)+2;
                if(num1>num2)
                {
                    ans=num1-num2;
                    number1.setText(num1+"");
                    number2.setText(num2+"");
                }
                else
                {
                    ans=num2-num1;
                    number1.setText(num2+"");
                    number2.setText(num1+"");
                }
                operator.setText("-");
                break;
            case 2:
                num1=random.nextInt(99)+2;
                num2=random.nextInt(11)+2;
                ans=num1*num2;
                number1.setText(num1+"");
                number2.setText(num2+"");
                operator.setText("×");
                break;
            case 3:
                num1 = random.nextInt(99) + 2;
                num2 = random.nextInt(11) + 2;
                ans = num1;
                num1 = num2 * num1;
                number1.setText(num1+"");
                number2.setText(num2+"");
                operator.setText("÷");
                break;

        }

    }

    public void checker()
    {
        String input = answer.getText().toString();
        Integer inans=0;
        Boolean cont=true;
        try{
            inans = parseInt(input);
        }
        catch (Exception e)
        {
            cont=false;
        }
        if(cont)
        {
            if(ans.equals(inans))
            {
                Integer ts = parseInt(score.getText().toString());
                score.setText(++ts+"");
                answer.setText("");
                gensetter();
            }
        }
    }

    public void stopgame()
    {
        interval=120;
        gamepanel.setVisibility(View.INVISIBLE);
        tt.setVisibility(View.INVISIBLE);
        st.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        timer.setVisibility(View.INVISIBLE);
        finalscore.setText(score.getText().toString());
        score.setText(0+"");
        scorepanel.setVisibility(View.VISIBLE);

    }

}
