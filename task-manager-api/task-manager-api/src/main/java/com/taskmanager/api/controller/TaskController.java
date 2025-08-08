package com.taskmanager.api.controller;

import com.taskmanager.api.dto.TaskRequestDTO;
import com.taskmanager.api.dto.TaskResponseDTO;
import com.taskmanager.api.entity.TaskStatus;
import com.taskmanager.api.entity.User;
import com.taskmanager.api.repository.UserRepository;
import com.taskmanager.api.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Endpoints para gerenciamento de tarefas")
public class TaskController {

    private final TaskService taskService;
    private final UserRepository userRepository;

    public TaskController(TaskService taskService, UserRepository userRepository) {
        this.taskService = taskService;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    @Operation(summary = "Cria uma nova tarefa para o usuário autenticado",
            description = "Cria uma nova tarefa com título, descrição e data de vencimento.")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO taskRequestDTO) {
        User currentUser = getCurrentUser();
        TaskResponseDTO createdTask = taskService.createTask(taskRequestDTO, currentUser);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retorna todas as tarefas do usuário autenticado",
            description = "Retorna uma lista de todas as tarefas do usuário logado.")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        User currentUser = getCurrentUser();
        List<TaskResponseDTO> tasks = taskService.getAllTasksByUser(currentUser);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma tarefa por ID",
            description = "Retorna uma tarefa específica pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada ou usuário não autorizado")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        TaskResponseDTO task = taskService.getTaskById(id, currentUser);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma tarefa existente",
            description = "Atualiza uma tarefa específica pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada ou usuário não autorizado")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO taskRequestDTO) {
        User currentUser = getCurrentUser();
        TaskResponseDTO updatedTask = taskService.updateTask(id, taskRequestDTO, currentUser);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma tarefa por ID",
            description = "Deleta uma tarefa específica pelo seu ID.")
    @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada ou usuário não autorizado")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        taskService.deleteTask(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    @Operation(summary = "Filtra tarefas por status",
            description = "Retorna uma lista de tarefas do usuário logado, filtradas por status (PENDING, COMPLETED, OVERDUE).")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas filtrada retornada com sucesso")
    @ApiResponse(responseCode = "401", description = "Não autenticado")
    public ResponseEntity<List<TaskResponseDTO>> filterTasksByStatus(@RequestParam TaskStatus status) {
        User currentUser = getCurrentUser();
        List<TaskResponseDTO> tasks = taskService.filterTasksByStatus(status, currentUser);
        return ResponseEntity.ok(tasks);
    }
}