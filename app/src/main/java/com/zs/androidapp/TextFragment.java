package com.zs.androidapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

class TextFragment extends Fragment {
    public TextFragment(){
        super(R.layout.text_fragment);
        Bundle result = new Bundle();
        result.putString("resultKey", "0");
        getParentFragmentManager().setFragmentResult("resultKey", result);
    }
}
