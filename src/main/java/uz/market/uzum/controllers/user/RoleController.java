package uz.market.uzum.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.user.UserRole;
import uz.market.uzum.dtos.roles.UserRoleDTO;
import uz.market.uzum.dtos.roles.UserRolePermissionDTO;
import uz.market.uzum.services.roles.UserRoleService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/role")
@Tag(name = "Role", description = "Play with roles")
public class RoleController {

    private final UserRoleService userRoleService;


    @Operation(summary = "This API used for creating a role",
            description = "This endpoint was designed for creating a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Unique role code violation",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UserRole> create(@Valid UserRoleDTO dto) {
        UserRole role = userRoleService.save(dto);
        return ResponseEntity.status(201).body(role);
    }


    @Operation(summary = "This API used for updating a role",
            description = "This endpoint was designed for updating a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Unique role code violation",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserRole> update(@PathVariable Integer id, @Valid UserRoleDTO dto) {
        UserRole role = userRoleService.update(id, dto);
        return ResponseEntity.ok(role);
    }

    @Operation(summary = "This API was designed for getting full information of role",
            description = "This endpoint was designed for getting full information of role")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserRole> get(@PathVariable Integer id) {
        UserRole role = userRoleService.get(id);
        return ResponseEntity.ok(role);
    }


    @Operation(summary = "This API was designed for deleting a role",
            description = "This endpoint was designed for deleting a role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role deleted successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Role not found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserRole> delete(@PathVariable Integer id) {
        userRoleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "This API was designed for getting all roles",
            description = "This endpoint was designed for getting all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles found successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Roles not found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @GetMapping("/roles")
    public ResponseEntity<List<UserRole>> roles() {
        List<UserRole> roleList = userRoleService.getRoles();
        return ResponseEntity.ok(roleList);
    }

    @Operation(summary = "This api is used for adding multiple permissions to role",
            description = "This endpoint is used for adding multiple permissions to role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission added successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @PostMapping("/addPermission")
    public ResponseEntity<UserRole> addPermission(@Valid UserRolePermissionDTO dto) {
        UserRole role = userRoleService.addPermission(dto);
        return ResponseEntity.ok(role);
    }


    @Operation(summary = "This api is used for removing multiple permissions from role",
            description = "This endpoint is used for removing multiple permissions from role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission removed successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserRole.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    @DeleteMapping("/removePermission")
    public ResponseEntity<UserRole> removePermission(@Valid UserRolePermissionDTO dto) {
        UserRole role = userRoleService.removePermission(dto);
        return ResponseEntity.ok(role);
    }


}
