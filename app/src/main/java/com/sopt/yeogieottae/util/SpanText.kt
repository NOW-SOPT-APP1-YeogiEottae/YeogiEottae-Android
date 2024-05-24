package com.sopt.yeogieottae.util

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TextAppearanceSpan
import androidx.core.content.ContextCompat
import com.sopt.yeogieottae.R


fun bulletSpanText(
    context: Context,
    texts: List<String>,
    bulletColor: Int = ContextCompat.getColor(context, R.color.gray_700)
): SpannableStringBuilder {
    val spannableStringBuilder = SpannableStringBuilder()

    for ((index, text) in texts.withIndex()) {
        val spannableString = SpannableString(text)
        val bulletSpan = BulletSpan(3, bulletColor, 1)

        spannableString.setSpan(
            bulletSpan,
            0,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringBuilder.append(spannableString)
        if (index < texts.size - 1) {
            spannableStringBuilder.append("\n")
        }
    }
    return spannableStringBuilder
}

fun bulletSpanTextCancel(
    context: Context,
    texts: List<String>,
    bulletColor: Int = ContextCompat.getColor(context, R.color.gray_700)
): SpannableStringBuilder {
    val spannableStringBuilder = SpannableStringBuilder()
    for ((index, text) in texts.withIndex()) {
        val spannableString = SpannableString(text)
        val bulletSpan = BulletSpan(3, bulletColor, 1)

        spannableString.setSpan(
            bulletSpan,
            0,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        if (index == 3) {
            spannableString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.cuation)),
                0,
                spannableString.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                TextAppearanceSpan(
                    context,
                    R.style.TextAppearance_Yeogieotte_Pretendard_h5
                ),
                0,
                spannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        if (index in 4..5) {
            spannableString.setSpan(
                TextAppearanceSpan(
                    context,
                    R.style.TextAppearance_Yeogieotte_Pretendard_h5
                ),
                0,
                spannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        spannableStringBuilder.append(spannableString)

        if (index < texts.size - 1) {
            spannableStringBuilder.append("\n")
        }
    }
    return spannableStringBuilder
}
