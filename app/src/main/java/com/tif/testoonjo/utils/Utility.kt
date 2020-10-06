package com.tif.testoonjo.utils
import android.content.Context
import android.util.DisplayMetrics
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.concurrent.Executors

class Utility
{
    companion object {

        fun validateForEmptyEditText(vararg strings:String):Boolean
        {
            var isEmpty = false
            for(string in strings)
            {
                if(string.isEmpty())
                {
                    isEmpty = true
                }
            }
            return isEmpty
        }
    }


}

fun convertDpToPixel(dp: Int, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun convertPixelsToDp(px: Int, context: Context): Float {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun formatCurrency(currency: String?): String {
    if (currency == null) {
        return ""
    }
    if (currency.length == 0) {
        return ""
    }

    var price: Double
    try {
        price = java.lang.Double.parseDouble(currency)
    } catch (e: NumberFormatException) {
        price = 0.0
    }

    val kursIndonesia = DecimalFormat.getCurrencyInstance() as DecimalFormat
    kursIndonesia.applyPattern("###,###")
    val formatRp = DecimalFormatSymbols()
    formatRp.currencySymbol = ""
    formatRp.groupingSeparator = '.'
    kursIndonesia.decimalFormatSymbols = formatRp
    return kursIndonesia.format(price)
}

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}