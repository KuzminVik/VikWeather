package ru.geekbrains.myweather

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.geekbrains.myweather.presentation.search.ValidateInputText

class ValidateNameCityTest {

    @Test
    fun validateNameCity_NameIsEmpty_returnFalse(){
        assertFalse(ValidateInputText.validateNameCity(""))
    }

    @Test
    fun validateNameCity_NameIsNull_returnFalse(){
        assertFalse(ValidateInputText.validateNameCity(null))
    }

    @Test
    fun validateNameCity_NameIsCorrect_returnTrue(){
        assertTrue(ValidateInputText.validateNameCity("Рим"))
    }

    @Test
    fun validateNameCity_NameIsTwospace_returnFalse(){
        assertFalse(ValidateInputText.validateNameCity("Нью  Йорк"))
    }

}