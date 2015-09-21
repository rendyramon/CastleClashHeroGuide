package com.erayarslan.castleclashheroguide.util;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AdManager {
    private InterstitialAd interstitialAd;

    public AdManager(Context context) {
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId("ca-app-pub-2770593653774664/3457548239");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                load();
            }
        });
        load();
    }

    private void load() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("705C1B2695585EED171AD75C0DB9EF5D").build();
        interstitialAd.loadAd(adRequest);
    }

    public void show() {
        if(interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
    }
}
