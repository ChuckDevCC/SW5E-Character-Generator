package com.chuckdevcc.sw5echaractergenerator

import android.content.Intent
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chuckdevcc.sw5echaractergenerator.R.id.fragment_container
import java.util.*


// database table for the character sheet.
// the idea is that it will pull the values entered by the user
// and store in the database, populating the list
// in CharacterList.kt.
@Entity
data class CharacterSheet(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var name: String = "",
    var strength: Int = 12,
    var dexterity: Int = 13,
    var constitution: Int = 14,
    var intelligence: Int = 15,
    var wisdom: Int = 16,
    var charisma: Int = 17
)


// name all our variables and interactables
class CreateCharacterFragment : Fragment() {

    private lateinit var cName: EditText
    private lateinit var cClass: EditText
    private lateinit var cLevel: EditText
    private lateinit var cRace: EditText

    private lateinit var buttonVendor: Button
    private lateinit var buttonPilot: Button
    private lateinit var buttonWarrior: Button
    private lateinit var buttonCorruptor: Button
    private lateinit var buttonKnight: Button
    private lateinit var buttonConsular: Button

    private lateinit var preset: TextView
    private lateinit var presetClear: Button

    private lateinit var buttonRandom: Button

    private lateinit var cSTR: EditText
    private lateinit var cDEX: EditText
    private lateinit var cCON: EditText
    private lateinit var cINT: EditText
    private lateinit var cWIS: EditText
    private lateinit var cCHA: EditText

    private lateinit var buttonGen: Button
    private lateinit var buttonTake: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newInstance(): CreateCharacterFragment {
            return CreateCharacterFragment()
        }
    }

    // inflate the view on creation
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_create, container, false)

        // find all the variables by view the view controller
        // and store them in a storable variable.
        cName = view.findViewById(R.id.characterName)
        cClass = view.findViewById(R.id.characterClass)
        cLevel = view.findViewById(R.id.characterLevel)
        cRace = view.findViewById(R.id.characterRace)

        buttonVendor = view.findViewById(R.id.buttonVendor)
        buttonPilot = view.findViewById(R.id.buttonTrades)
        buttonWarrior = view.findViewById(R.id.buttonSW)
        buttonCorruptor = view.findViewById(R.id.buttonSC)
        buttonKnight = view.findViewById(R.id.buttonJK)
        buttonConsular = view.findViewById(R.id.buttonJC)

        preset = view.findViewById(R.id.presetResult)
        presetClear = view.findViewById(R.id.presetClear)

        buttonRandom = view.findViewById(R.id.checkRandom)

        cSTR = view.findViewById(R.id.statSTR)
        cDEX = view.findViewById(R.id.statDEX)
        cCON = view.findViewById(R.id.statCON)
        cINT = view.findViewById(R.id.statINT)
        cWIS = view.findViewById(R.id.statWIS)
        cCHA = view.findViewById(R.id.statCHA)

        buttonGen = view.findViewById(R.id.buttonGen)
        buttonTake = view.findViewById(R.id.buttonTake)

        return view
    }


    override fun onStart() {
        super.onStart()

        // randomize the values in the boxes
        // between 8-18. Usually, a stat distribution is
        // from 0-20, but this method is better and more 'fair'
        buttonRandom.setOnClickListener() {

            val randomSTR = (8..18).random()
            val randomDEX = (8..18).random()
            val randomCON = (8..18).random()
            val randomINT = (8..18).random()
            val randomWIS = (8..18).random()
            val randomCHA = (8..18).random()

            cSTR.setText(randomSTR.toString())
            cDEX.setText(randomDEX.toString())
            cCON.setText(randomCON.toString())
            cINT.setText(randomINT.toString())
            cWIS.setText(randomWIS.toString())
            cCHA.setText(randomCHA.toString())

        }

        // These are presets that give special attacks and abilities,
        // so the DM does not have to keep track.
        buttonVendor.setOnClickListener() {
            preset.setText("Preset: Vendor")
        }

        buttonPilot.setOnClickListener() {
            preset.setText("Preset: Pilot/Mechanic")
        }

        buttonWarrior.setOnClickListener() {
            preset.setText("Preset: Sith Warrior")
        }

        buttonCorruptor.setOnClickListener() {
            preset.setText("Preset: Sith Corruptor")
        }

        buttonKnight.setOnClickListener() {
            preset.setText("Preset: Jedi Knight")
        }

        buttonConsular.setOnClickListener() {
            preset.setText("Preset: Jedi Consular")
        }

        presetClear.setOnClickListener() {
            preset.setText("Preset: None")
        }

        // swap to character sheet fragment on button press
        val newFragment = CharacterDisplayFragment()
        val fragmentManager = (activity as FragmentActivity).supportFragmentManager
        buttonGen.setOnClickListener() {

            fragmentManager
                .beginTransaction()
                .replace(fragment_container, newFragment)
                .commit()
        }
    }


}


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}