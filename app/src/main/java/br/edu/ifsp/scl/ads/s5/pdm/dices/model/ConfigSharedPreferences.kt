package br.edu.ifsp.scl.ads.s5.pdm.dices.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class ConfigSharedPreferences(context: Context): ConfigDao {
    private val CONFIG_SHARED_PREFERENCES = "configSharedPreferences"
    private val CONFIG_KEY = "config"
    private val configSp: SharedPreferences = context.getSharedPreferences(
        CONFIG_SHARED_PREFERENCES,
        Context.MODE_PRIVATE
    )

    private val gson: Gson = Gson()

    override fun saveConfig(config: Config) {
        val editor = configSp.edit()
        editor.putString(CONFIG_KEY, gson.toJson(config))
        editor.apply()
    }

    override fun readConfig(): Config {
        val configString = configSp.getString(CONFIG_KEY, "")

        var config = gson.fromJson(configString, Config::class.java)
        if (config == null) {
            config = Config()
            saveConfig(config)
        }
        return config
    }
}