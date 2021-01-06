package br.edu.ifsp.scl.ads.s5.pdm.dices.controller

import br.edu.ifsp.scl.ads.s5.pdm.dices.model.Config
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.ConfigDao
import br.edu.ifsp.scl.ads.s5.pdm.dices.model.ConfigSharedPreferences
import br.edu.ifsp.scl.ads.s5.pdm.dices.view.MainActivity

class ConfigController(mainActivity: MainActivity) {
    val configDao: ConfigDao
    init {
        configDao = ConfigSharedPreferences(mainActivity)
    }

    fun getSavedConfig() = configDao.readConfig()
    fun saveConfig(config: Config) = configDao.saveConfig(config)
}