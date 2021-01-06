package br.edu.ifsp.scl.ads.s5.pdm.dices.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Config (
    var qtdDados: Int = 1,
    var qtdFaces: Int = 6
): Parcelable