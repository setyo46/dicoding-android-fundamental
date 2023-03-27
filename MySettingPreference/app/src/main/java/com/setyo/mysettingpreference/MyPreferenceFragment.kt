package com.setyo.mysettingpreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

class MyPreferenceFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    private var name: String? = null
    private var email: String? = null
    private var phone: String? = null
    private var age: String? = null
    private var love: String? = null

    private lateinit var nameReference: EditTextPreference
    private lateinit var emailReference: EditTextPreference
    private lateinit var phoneReference: EditTextPreference
    private lateinit var ageReference: EditTextPreference
    private lateinit var isLoveChelseaReference: CheckBoxPreference


    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()

    }

    /*
    Untuk manajemen lifecycle yang tepat dalam Kegiatan atau Fragment ,
    kita wajib register dan unregister listener nya di onResume () dan onPause ()
     */
    override fun onResume() {
        super.onResume()
        preferenceScreen
            .sharedPreferences
            ?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen
            .sharedPreferences
            ?.registerOnSharedPreferenceChangeListener(this)
    }

    /*
        Inisiasi preferences
   */
    private fun init() {
        /* Ini digunakan untuk mendapatkan key dari strings.xml*/
        name = resources.getString(R.string.name)
        email = resources.getString(R.string.email)
        phone = resources.getString(R.string.phone_number)
        age = resources.getString(R.string.age)
        love = resources.getString(R.string.is_love)

        /* Masing masing Preference dicocokan berdasarkan dari KEY preference tersebut*/
        nameReference = findPreference<EditTextPreference>(name!!) as EditTextPreference
        emailReference = findPreference<EditTextPreference>(email!!) as EditTextPreference
        phoneReference = findPreference<EditTextPreference>(phone!!) as EditTextPreference
        ageReference = findPreference<EditTextPreference>(age!!) as EditTextPreference
        isLoveChelseaReference = findPreference<CheckBoxPreference>(love!!) as CheckBoxPreference
    }

    /*
   Set summary menggunakan preference
    */
    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        if (sh != null) {
            nameReference.summary = sh.getString(name, DEFAULT_VALUE)
            emailReference.summary = sh.getString(email, DEFAULT_VALUE)
            phoneReference.summary = sh.getString(phone, DEFAULT_VALUE)
            ageReference.summary = sh.getString(age, DEFAULT_VALUE)
            isLoveChelseaReference.isChecked = sh.getBoolean(love, false)
        }
    }

    companion object {
        private const val DEFAULT_VALUE = "Tidak Ada"

    }

    /*
    listener untuk set summary ketika ada sharedpreference yang berubah
     */
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == name) {
            nameReference.summary = sharedPreferences.getString(name, DEFAULT_VALUE)
        }

        if (key == email) {
            emailReference.summary = sharedPreferences.getString(email, DEFAULT_VALUE)
        }

        if (key == phone) {
            phoneReference.summary = sharedPreferences.getString(phone, DEFAULT_VALUE)
        }

        if (key == age) {
            ageReference.summary = sharedPreferences.getString(age, DEFAULT_VALUE)
        }

        if (key == love) {
            isLoveChelseaReference.isChecked = sharedPreferences.getBoolean(love, false)
        }
    }
}