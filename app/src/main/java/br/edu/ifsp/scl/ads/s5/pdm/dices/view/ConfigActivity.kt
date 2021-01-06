package br.edu.ifsp.scl.ads.s5.pdm.dices.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.get
import br.edu.ifsp.scl.ads.s5.pdm.dices.R
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityConfigBinding
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.Config

class ConfigActivity : AppCompatActivity(){
    private lateinit var  activityConfigBinding: ActivityConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityConfigBinding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(activityConfigBinding.root)

        var oldConfig: Config? = intent.getParcelableExtra(MainActivity.Extras.CONFIG_ATUAL)
        if (oldConfig == null) {
            oldConfig = Config()
        }
        val radioGroup = activityConfigBinding.qtdDadosRg
        (radioGroup.getChildAt(oldConfig.qtdDados-1) as RadioButton).isChecked = true
        activityConfigBinding.qtdFacesEt.setText(oldConfig.qtdFaces.toString())

        activityConfigBinding.salvarConfigBt.setOnClickListener {
            val qtdFacesEt = activityConfigBinding.qtdFacesEt.text.toString()
            var qtdFacesDado = 6
            if (qtdFacesEt.isNotEmpty() && qtdFacesEt.isDigitsOnly() &&  qtdFacesEt.toInt() > 0) {
                qtdFacesDado = qtdFacesEt.toInt()
            }
            val novaConfig = Config(
                findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString().toInt(),
                qtdFacesDado
            )

            val retornoIntent = Intent()
            retornoIntent.putExtra(MainActivity.Extras.NOVA_CONFIG, novaConfig)
            setResult(RESULT_OK, retornoIntent)
            finish()
        }
    }
}