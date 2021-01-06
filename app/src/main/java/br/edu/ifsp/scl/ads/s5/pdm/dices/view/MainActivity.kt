package br.edu.ifsp.scl.ads.s5.pdm.dices.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.s5.pdm.dices.R
import br.edu.ifsp.scl.ads.s5.pdm.dices.controller.ConfigController
import br.edu.ifsp.scl.ads.s5.pdm.dices.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.Config
import br.edu.ifsp.scl.ads.s5.pdm.dices.view.MainActivity.Extras.CONFIG_ATUAL
import br.edu.ifsp.scl.ads.s5.pdm.dices.view.MainActivity.Extras.NOVA_CONFIG
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    private lateinit var configController: ConfigController

    private lateinit var config: Config

    private val CONFIGURACOES_REQUEST_CODE = 0
    object Extras {
        val NOVA_CONFIG = "NOVA_CONFIG"
        val CONFIG_ATUAL = "CONFIG_ATUAL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        configController = ConfigController(this)

        config = configController.getSavedConfig()
    }

    fun onClick(view: View) {
        if (view == activityMainBinding.JogarBt) {
//            Random do primeiro dado
            val resultado: Int = Random(System.currentTimeMillis()).nextInt(config.qtdFaces) + 1
            var displayResultTxt = resultado.toString()

//            Display da imagem do primeiro dado
            val resultadoImagem = "dice_$resultado"
            activityMainBinding.resultado1Iv.setImageResource(
                resources.getIdentifier(resultadoImagem, "drawable", packageName)
            )
            if (config.qtdDados == 2){
                activityMainBinding.resultado2Iv.visibility = VISIBLE

//            Random do segundo dado
                val resultado: Int = Random(System.currentTimeMillis()).nextInt(config.qtdFaces) + 1
                displayResultTxt += " | $resultado"

//            Display da imagem do primeiro dado
                val resultadoImagem = "dice_$resultado"
                activityMainBinding.resultado2Iv.setImageResource(
                    resources.getIdentifier(resultadoImagem, "drawable", packageName)
                )
            }
            else {
                activityMainBinding.resultado2Iv.visibility = INVISIBLE
            }
            activityMainBinding.resultadoTv.text = displayResultTxt
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        if (item.itemId == R.id.configuracoesMi){
            val configIntent = Intent(this, ConfigActivity::class.java)
            configIntent.putExtra(CONFIG_ATUAL, config)
            startActivityForResult(configIntent, CONFIGURACOES_REQUEST_CODE)
            true
        }
        else
            false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CONFIGURACOES_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val config = data.getParcelableExtra<Config>(NOVA_CONFIG)
            if (config != null){
                configController.saveConfig(config)
                setConfig(config)
            }
        }
    }

    private fun setConfig(config: Config) {
        this.config = config
//        "todo update dos parametros de random e imagem"
    }
}