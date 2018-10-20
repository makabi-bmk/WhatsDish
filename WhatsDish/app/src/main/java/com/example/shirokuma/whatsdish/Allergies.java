package com.example.shirokuma.whatsdish;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import java.util.HashMap;

public class Allergies  extends AppCompatActivity {
    public HashMap<String, String> allergiesList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allergies);

//        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.apple_b);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Allergies.this, Question.class);
//                startActivity(intent);
//            }
//        });

        final ImageButton shrimp = (ImageButton)findViewById(R.id.shrimp);
        shrimp.setOnClickListener(new View.OnClickListener() {

            int shrimp_flag = 0;
            @Override
            public void onClick(View view) {
                if (shrimp_flag == 0) {
                    allergiesList.put("shrimp", "えび");
                    shrimp_flag = 1;
                    shrimp.setImageResource(R.drawable.shrimp_a);
                } else {
                    allergiesList.remove("shrimp");
                    shrimp_flag = 0;
                    shrimp.setImageResource(R.drawable.shrimp_b);
                }
            }
        });

        final ImageButton crub = (ImageButton)findViewById(R.id.crub);
        crub.setOnClickListener(new View.OnClickListener() {

            int crub_flag = 0;
            @Override
            public void onClick(View view) {
                if (crub_flag == 0) {
                    allergiesList.put("crub", "かに");
                    crub_flag = 1;
                    crub.setImageResource(R.drawable.crub_a);
                } else {
                    allergiesList.remove("crub");
                    crub_flag = 0;
                    crub.setImageResource(R.drawable.crub_b);
                }

            }
        });

        final ImageButton buckwheat = (ImageButton)findViewById(R.id.buckwheat);
        buckwheat.setOnClickListener(new View.OnClickListener() {
            int buckwheat_flag = 0;
            @Override
            public void onClick(View view) {
                if (buckwheat_flag == 0) {
                    allergiesList.put("buckwheat", "そば");
                    buckwheat_flag = 1;
                    buckwheat.setImageResource(R.drawable.buckwheat_a);
                } else {
                    allergiesList.remove("buckwheat");
                    buckwheat_flag = 0;
                    buckwheat.setImageResource(R.drawable.buckwheat_b);
                }

            }
        });

        final ImageButton wheat = (ImageButton)findViewById(R.id.wheat);
        wheat.setOnClickListener(new View.OnClickListener() {
            int wheat_flag = 0;
            @Override
            public void onClick(View view) {
                if (wheat_flag == 0) {
                    allergiesList.put("wheat", "小麦");
                    wheat_flag = 1;
                    wheat.setImageResource(R.drawable.wheat_a);
                } else {
                    allergiesList.remove("wheat");
                    wheat_flag = 0;
                    wheat.setImageResource(R.drawable.wheat_b);
                }
            }
        });

        final ImageButton egg = (ImageButton)findViewById(R.id.egg);
        egg.setOnClickListener(new View.OnClickListener() {
            int egg_flag = 0;
            @Override
            public void onClick(View view) {
                if (egg_flag == 0) {
                    allergiesList.put("egg", "卵");
                    egg_flag = 1;
                    egg.setImageResource(R.drawable.egg_a);
                } else {
                    allergiesList.remove("egg");
                    egg_flag = 0;
                    egg.setImageResource(R.drawable.egg_b);
                }

            }
        });

        final ImageButton milk = (ImageButton)findViewById(R.id.milk);
        milk.setOnClickListener(new View.OnClickListener() {
            int milk_flag = 0;
            @Override
            public void onClick(View view) {
                if (milk_flag == 0) {
                    allergiesList.put("milk", "牛乳");
                    milk_flag = 1;
                    milk.setImageResource(R.drawable.milk_a);
                } else {
                    allergiesList.remove("milk");
                    milk_flag = 0;
                    milk.setImageResource(R.drawable.milk_b);
                }
            }
        });

        final ImageButton peanuts = (ImageButton)findViewById(R.id.peanuts);
        peanuts.setOnClickListener(new View.OnClickListener() {
            int peanuts_flag = 0;
            @Override
            public void onClick(View view) {
                if (peanuts_flag == 0) {
                    allergiesList.put("peanuts", "落花生");
                    peanuts_flag = 1;
                    peanuts.setImageResource(R.drawable.peanuts_a);
                } else {
                    allergiesList.remove("peanuts");
                    peanuts_flag = 0;
                    peanuts.setImageResource(R.drawable.peanuts_b);
                }
            }
        });

        final ImageButton squid = (ImageButton)findViewById(R.id.squid);
        squid.setOnClickListener(new View.OnClickListener() {
            int squid_flag = 0;
            @Override
            public void onClick(View view) {
                if (squid_flag == 0) {
                    allergiesList.put("squid", "イカ");
                    squid_flag = 1;
                    squid.setImageResource(R.drawable.squid_a);
                } else {
                    allergiesList.remove("squid");
                    squid_flag = 0;
                    squid.setImageResource(R.drawable.squid_b);
                }
            }
        });

        final ImageButton salmon_roe = (ImageButton)findViewById(R.id.salmon_roe);
        salmon_roe.setOnClickListener(new View.OnClickListener() {
            int salmon_roe_flag = 0;
            @Override
            public void onClick(View view) {
                if (salmon_roe_flag == 0) {
                    allergiesList.put("salmon_roe", "いくら");
                    salmon_roe_flag = 1;
                    salmon_roe.setImageResource(R.drawable.salmon_roe_a);
                } else {
                    allergiesList.remove("salmon_roe");
                    salmon_roe_flag = 0;
                    salmon_roe.setImageResource(R.drawable.salmon_roe_b);
                }
            }
        });

        final ImageButton orange = (ImageButton)findViewById(R.id.orange);
        orange.setOnClickListener(new View.OnClickListener() {
            int orange_flag = 0;
            @Override
            public void onClick(View view) {
                if (orange_flag == 0) {
                    allergiesList.put("orange", "オレンジ");
                    orange_flag = 1;
                    orange.setImageResource(R.drawable.orange_a);
                } else {
                    allergiesList.remove("orange");
                    orange_flag = 0;
                    orange.setImageResource(R.drawable.orange_b);
                }
            }
        });

        final ImageButton cashewnuts = (ImageButton)findViewById(R.id.cashewnuts);
        cashewnuts.setOnClickListener(new View.OnClickListener() {
            int cashewnuts_flag = 0;
            @Override
            public void onClick(View view) {
                if (cashewnuts_flag == 0) {
                    allergiesList.put("cashewnuts", "カシューナッツ");
                    cashewnuts_flag = 1;
                    cashewnuts.setImageResource(R.drawable.cashewnuts_a);
                } else {
                    allergiesList.remove("cashewnuts");
                    cashewnuts_flag = 0;
                    cashewnuts.setImageResource(R.drawable.cashewnuts_b);
                }
            }
        });

        final ImageButton kiwi = (ImageButton)findViewById(R.id.kiwi);
        kiwi.setOnClickListener(new View.OnClickListener() {
            int kiwi_flag = 0;
            @Override
            public void onClick(View view) {
                if (kiwi_flag == 0) {
                    allergiesList.put("kiwi", "キウイ");
                    kiwi_flag = 1;
                    kiwi.setImageResource(R.drawable.kiwi_a);
                } else {
                    allergiesList.remove("kiwi");
                    kiwi_flag = 0;
                    kiwi.setImageResource(R.drawable.kiwi_b);
                }
            }
        });

        final ImageButton cow = (ImageButton)findViewById(R.id.cow);
        cow.setOnClickListener(new View.OnClickListener() {
            int cow_flag = 0;
            @Override
            public void onClick(View view) {
                if (cow_flag == 0) {
                    allergiesList.put("cow", "牛肉");
                    cow_flag = 1;
                    cow.setImageResource(R.drawable.cow_a);
                } else {
                    allergiesList.remove("cow");
                    cow_flag = 0;
                    cow.setImageResource(R.drawable.cow_b);
                }
            }
        });

        final ImageButton walnut = (ImageButton)findViewById(R.id.walnut);
        walnut.setOnClickListener(new View.OnClickListener() {
            int walnut_flag = 0;
            @Override
            public void onClick(View view) {
                if (walnut_flag == 0) {
                    allergiesList.put("walnut", "くるみ");
                    walnut_flag = 1;
                    walnut.setImageResource(R.drawable.walnut_a);
                } else {
                    allergiesList.remove("walnut");
                    walnut_flag = 0;
                    walnut.setImageResource(R.drawable.walnut_b);
                }
            }
        });

        final ImageButton sesame = (ImageButton)findViewById(R.id.sesame);
        sesame.setOnClickListener(new View.OnClickListener() {
            int sesame_flag = 0;
            @Override
            public void onClick(View view) {
                if (sesame_flag == 0) {
                    allergiesList.put("sesame", "ごま");
                    sesame_flag = 1;
                    sesame.setImageResource(R.drawable.sesame_a);
                } else {
                    allergiesList.remove("sesame");
                    sesame_flag = 0;
                    sesame.setImageResource(R.drawable.sesame_b);
                }
            }
        });

        final ImageButton fish = (ImageButton)findViewById(R.id.fish);
        fish.setOnClickListener(new View.OnClickListener() {
            int fish_flag = 0;
            @Override
            public void onClick(View view) {
                if (fish_flag == 0) {
                    allergiesList.put("fish", "魚");
                    fish_flag = 1;
                    fish.setImageResource(R.drawable.fish_a);
                } else {
                    allergiesList.remove("fish");
                    fish_flag = 0;
                    fish.setImageResource(R.drawable.fish_b);
                }
            }
        });

        final ImageButton soy = (ImageButton)findViewById(R.id.soy);
        soy.setOnClickListener(new View.OnClickListener() {
            int soy_flag = 0;
            @Override
            public void onClick(View view) {
                if (soy_flag == 0) {
                    allergiesList.put("soy ", "大豆");
                    soy_flag = 1;
                    soy.setImageResource(R.drawable.soy_a);
                } else {
                    allergiesList.remove("soy");
                    soy_flag = 0;
                    soy.setImageResource(R.drawable.soy_b);
                }
            }
        });

        final ImageButton chicken = (ImageButton)findViewById(R.id.chicken);
        chicken.setOnClickListener(new View.OnClickListener() {
            int chicken_flag = 0;
            @Override
            public void onClick(View view) {
                if (chicken_flag == 0) {
                    allergiesList.put("chicken", "鶏肉");
                    chicken_flag = 1;
                    chicken.setImageResource(R.drawable.chicken_a);
                } else {
                    allergiesList.remove("chicken");
                    chicken_flag = 0;
                    chicken.setImageResource(R.drawable.chicken_b);
                }
            }
        });

        final ImageButton banana = (ImageButton)findViewById(R.id.banana);
        banana.setOnClickListener(new View.OnClickListener() {
            int banana_flag = 0;
            @Override
            public void onClick(View view) {
                if (banana_flag == 0) {
                    allergiesList.put("banana", "バナナ");
                    banana_flag = 1;
                    banana.setImageResource(R.drawable.banana_a);
                } else {
                    allergiesList.remove("banana");
                    banana_flag = 0;
                    banana.setImageResource(R.drawable.banana_b);
                }
            }
        });

        final ImageButton pig = (ImageButton)findViewById(R.id.pig);
        pig.setOnClickListener(new View.OnClickListener() {
            int pig_flag = 0;
            @Override
            public void onClick(View view) {
                if (pig_flag == 0) {
                    allergiesList.put("pig", "豚肉");
                    pig_flag = 1;
                    pig.setImageResource(R.drawable.pig_a);
                } else {
                    allergiesList.remove("pig");
                    pig_flag = 0;
                    pig.setImageResource(R.drawable.pig_b);
                }
            }
        });

        final ImageButton mushroom = (ImageButton)findViewById(R.id.mushroom);
        mushroom.setOnClickListener(new View.OnClickListener() {
            int mushroom_flag = 0;
            @Override
            public void onClick(View view) {
                if (mushroom_flag == 0) {
                    allergiesList.put("mushroom", "きのこ");
                    mushroom_flag = 1;
                    mushroom.setImageResource(R.drawable.mushroom_a);
                } else {
                    allergiesList.remove("mushroom");
                    mushroom_flag = 0;
                    mushroom.setImageResource(R.drawable.mushroom_b);
                }
            }
        });

        final ImageButton peach = (ImageButton)findViewById(R.id.peach);
        peach.setOnClickListener(new View.OnClickListener() {
            int peach_flag = 0;
            @Override
            public void onClick(View view) {
                if (peach_flag == 0) {
                    allergiesList.put("peach", "もも");
                    peach_flag = 1;
                    peach.setImageResource(R.drawable.peach_a);
                } else {
                    allergiesList.remove("peach");
                    peach_flag = 0;
                    peach.setImageResource(R.drawable.peach_b);
                }
            }
        });

        final ImageButton yam = (ImageButton)findViewById(R.id.yam);
        yam.setOnClickListener(new View.OnClickListener() {
            int yam_flag = 0;
            @Override
            public void onClick(View view) {
                if (yam_flag == 0) {
                    allergiesList.put("yam", "やまいも");
                    yam_flag = 1;
                    yam.setImageResource(R.drawable.yam_a);
                } else {
                    allergiesList.remove("yam");
                    yam_flag = 0;
                    yam.setImageResource(R.drawable.yam_b);
                }
            }
        });

        final ImageButton apple = (ImageButton)findViewById(R.id.apple);
        apple.setOnClickListener(new View.OnClickListener() {
            int apple_flag = 0;
            @Override
            public void onClick(View view) {
                if (apple_flag == 0) {
                    allergiesList.put("apple", "りんご");
                    apple_flag = 1;
                    apple.setImageResource(R.drawable.apple_a);
                } else {
                    allergiesList.remove("apple");
                    apple_flag = 0;
                    apple.setImageResource(R.drawable.apple_b);
                }
            }
        });

        final ImageButton gelatin = (ImageButton)findViewById(R.id.gelatin);
        gelatin.setOnClickListener(new View.OnClickListener() {
            int gelatin_flag = 0;
            @Override
            public void onClick(View view) {
                if (gelatin_flag == 0) {
                    allergiesList.put("gelatin", "ゼラチン");
                    gelatin_flag = 1;
                    gelatin.setImageResource(R.drawable.gelatin_a);
                } else {
                    allergiesList.remove("gelatin");
                    gelatin_flag = 0;
                    gelatin.setImageResource(R.drawable.gelatin_b);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == android.view.KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
