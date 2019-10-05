package com.example.todoservice.controller

import com.example.todoservice.model.Todo
import com.example.todoservice.repo.TodoRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.reset
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class, SpringExtension::class)
internal class TodoControllerTest {

    @Mock
    lateinit var todoRepository: TodoRepository

     val todoController: TodoController= Mockito.mock(TodoController::class.java)

    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build()
    }

    @AfterEach
    fun tearDown() {
        reset(mockMvc)
    }

    @Test
    fun retrieveAllTodos() {
        given(todoRepository.findAll()).willReturn(listOf(Todo(100, "todo1"), Todo(101, "todo2")))

        mockMvc.perform(get("/api/todos").accept(APPLICATION_JSON))
                .andExpect(status().isOk)
        then(todoRepository).should().findAll()
    }

    @Test
    fun retrieveTodoById() {
    }

    @Test
    fun addTodo() {
    }

    @Test
    fun updateTodo() {
    }

    @Test
    fun deleteTodo() {
    }
}