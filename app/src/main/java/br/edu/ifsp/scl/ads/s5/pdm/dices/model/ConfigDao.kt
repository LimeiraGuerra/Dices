package br.edu.ifsp.scl.ads.s5.pdm.dices.model

interface ConfigDao {
    fun saveConfig(config: Config)
    fun readConfig(): Config
}