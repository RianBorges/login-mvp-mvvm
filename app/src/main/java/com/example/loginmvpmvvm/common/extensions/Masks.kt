package com.example.loginmvpmvvm.common.extensions

import android.widget.EditText
import com.example.loginmvpmvvm.common.masks.MaskEditText

fun EditText.setMask(mask: String) {
    addTextChangedListener(
        MaskEditText(
            mask,
            this
        )
    )
}