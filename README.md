
<img src= "https://imgur.com/EkvcqtN.png" width ="300">

# SW5E-Character-Generator 

Dungeons and Dragons has made a resurgence in popular media, with it's fifth edition being one of the most popular table-top games to date. 
A variation has been adapted to Star Wars, hence titled **"SW5E"**. This is a community that is consistently growing and modifying to be an enjoyable online experience.
While there are a multitude of applications for regular 'DnD', SW5E is still fairly fresh - that is the motivation behind this project.

### If there are Issues with the project, please contact me at `sandin2c@uregina.ca`

# Table of Contents
- [Installation][inst]
- [Application Vision][appv]
- [Building the Application(Design)][build]
- [Current Bugs and Limitations][bug]
- [Changelog][change]
- [References][ref]

# Installation

The files for this application can be downloaded as a `.zip` file or cloning a local repository on your machine. Once you have the files,
they can be opened in Android Studio.

## Programming in Android Studio

Once the project is open, Build the project with `CTRL+F9` or through the 'Build' tab. 
If you have a device plugged into your computer, you can run the application by changing the device and pressing the play arrow.
<img src = "https://imgur.com/JfLgNZy.png">

> If you wish to create an APK, you can navigate to the build menu, but select Build Bundle(s)/APK -> APK, and build from there.

You will see the files in the navigation menu to the left. You are now ready to program and modify the project!

# Application Vision

The Dungeon Master, the leader of the game session, is responsible for creating the *'Non-playable Characters' (NPC's)*. I wanted to build a simple application that can make
characters as easily as possible. It is meant to mimic a character sheet, like the one below:

<img src ="https://imgur.com/yoqk8ur.png" width = "350" height ="500">

It has a stat distrubition, the character's name, level, and race, alongside other things. To begin I wanted to just include the items that were mentioned, and slowly build up
extra features as time cotinued.

## Mockup Design

<p float = "left">
<img src = "https://imgur.com/UGLmahK.png" width = "250">
<img src = "https://imgur.com/ETXpL4f.png" width = "240" >
<img src = "https://imgur.com/1UpRmHT.png" width = "243" >
</p>

### Initial Page

There are four text fields for the user to enter, which will have hint windows letting them know what the input is asking for. There are six common archetypes the user
can select from, then enter the stats if they wish to, or the checkbox that will randomize them on character generation.

### List Page

After the character hits **"Take Me to the Character List"**, this window will appear. the user then can see a list of all the character names, select one, and it will
populate to the character sheet window.

### Character Sheet Page

After a user taps the Character they want, display the information to the user. It will have extra skills if the user selected a preset. This page is the one that can
adapt the most with future updates, adding new qualities, notes, and other features.

# Building the Application

## Design Philosophy
I decided to follow a *Model-View-ViewModel* architecture, with the application of fragments. While this app is simple in nature, it segments the files nicely
and we can work with the fragments individually, compared to working the whole application. This is done with code such as:

```kotlin

  val newFragment = CreateCharacterFragment()
supportFragmentManager
 .beginTransaction()
.add(R.id.main_container, newFragment)
 .commit() 
```

`supportFragmentManager` keeps track of which fragments are being used, which is helpful for system interactions, such as the back button.
Each window has a respective **Kotlin** and **XML** file, with a *ViewModel* and *Repisitory* file to interact with the database.

## Usage

<img src = "https://imgur.com/pDykhm3.gif">

This is a gif of the first page prototype. It allows text entry, a preset selection with updated text field, and a button that randomizes the stats.
> Note, In the original design it was a checkbox. I like how the button works better as the user can press it multiple times to get stats that
> they are pleased with before it submits.

Eventually, it would be able to submit as a database entry utilizing this piece of code:

```kotlin
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
```
Instead of the place holder int values, it would use the variables displayed on the screen and store in the database. This might mean they would have to
be strings instead of ints, or be typecast using a `.toInt()` addition.

# Current Bugs and Limitations

- I have some of the database designed, putting use in the SQLite provided by Android Studio, but it is not currently operational.
- `supportFragmentManager` now has transaction ID's, and that currently no longer lets the app switch fragments; it simply crashes.
- The list needs to populate with new dynamic characters being made.
- I would like to change the design of the application to be more in theme with the colors of SW5E, that being red and black.

# Changelog
 #### Any future additions or changes will be here!

# References

- Some code design is referenced from the [Big Nerd Ranch Guide][bnrg]. As I am still a beginner, 
this book provided a good tutorial on fragment design and programming in Android Studio.
- [Star Wars Fifth Edition][sw5e]

[bnrg]: <https://www.amazon.ca/Android-Programming-Nerd-Ranch-Guide/dp/0134706056/ref=sr_1_1?dchild=1&keywords=big+nerd+ranch+guide&qid=1619046100&sr=8-1>
[sw5e]: <https://sw5e.com/>
[inst]: <https://github.com/ChuckDevCC/SW5E-Character-Generator/new/main?readme=1#installation>
[appv]: <https://github.com/ChuckDevCC/SW5E-Character-Generator/new/main?readme=1#application-vision>
[build]: <https://github.com/ChuckDevCC/SW5E-Character-Generator/new/main?readme=1#building-the-application>
[bug]: <https://github.com/ChuckDevCC/SW5E-Character-Generator/new/main?readme=1#current-bugs-and-limitations>
[change]: <https://github.com/ChuckDevCC/SW5E-Character-Generator/new/main?readme=1#current-bugs-and-limitations>
[ref]: <https://github.com/ChuckDevCC/SW5E-Character-Generator/new/main?readme=1#references>
