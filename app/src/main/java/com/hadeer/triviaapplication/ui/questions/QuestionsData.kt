package com.hadeer.triviaapplication.ui.questions

object QuestionsData {
    private val Data : List<Question> = listOf(
        Question(
            1,
            "What is Android Jetpack?",
            listOf(
                "tools",
                "all of these",
                "documentation",
                "libraries"
            ),
1
        ),
        Question(
            2,
            "Base class for Layout?",
            listOf(
                "ViewSet",
                "ViewCollection",
                "ViewGroup",
                "ViewRoot"
            ),
            2
        ),
        Question(
            3,
            "Layout for complex Screens?",
            listOf(
                "ConstraintLayout",
                "GridLayout",
                "LinearLayout",
                "FrameLayout"
            ),
            0
        ),
        Question(
            4,
            "Pushing structured data into a Layout?",
            listOf(
                "Data Pushing",
                "Set Text",
                "OnClick",
                "Data Binding",
            ),
            3
        ),
        Question(
            5,
            "Inflate layout in fragments?",
            listOf(
                "onViewCreated",
                "onCreateLayout",
                "onInflateLayout",
                "onCreateView"
            ),
            3
        ), 
        Question(
            6,
            "Build system for Android?",
            listOf(
                "Graddle",
                "Gradle",
                "Grodle",
                "Groyle"
            ),
            1
        ),
        Question(
            7,
            "Android vector format?",
            listOf(
                "AndroidVectorDrawable",
                "DrawableVector",
                "VectorDrawable",
                "AndroidVector"
            ),
            2
        ),
        Question(
            8,
            "Android Navigation Component?",
            listOf(
                "NavCentral",
                "NavMaster",
                "NavSwitcher",
                "NavController"
            ),
            3
        ),
        Question(
            9,
            "Registers app with launcher?",
            listOf(
                "intent-filter",
                "app-registry",
                "launcher-registry",
                "app-launcher"
            ),
            0
        ),
        Question(
            10,
            "Mark a layout for Data Binding?",
            listOf(
                "<binding>",
                "<layout>",
                "<data-binding>",
                "<dbinding>"
            ),
            1
        )
    )

    fun getRandom():List<Question>{
        val randomData : MutableList<Question> = mutableListOf()
        val uniqueNums = (0..<Data.size).shuffled().take(4)
        for(i in uniqueNums){
            randomData.add(Data[i])
        }
        return randomData
    }
}