package uz.market.uzum.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.market.uzum.domains.user.UserPermission;
import uz.market.uzum.dtos.roles.UserPermissionCreateDTO;
import uz.market.uzum.services.roles.UserPermissionService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/permission")
@Tag(name = "Permission", description = "Permission API")
public class PermissionController {
    private final UserPermissionService permissionService;


    @Operation(summary = "This API used for creating a permission",
            description = "This endpoint was designed for creating a permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission created successfully", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema =
            @Schema(implementation = UserPermission.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema =
            @Schema(implementation = RuntimeException.class))}),
            @ApiResponse(responseCode = "400", description = "Unique permission code violation", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema =
            @Schema(implementation = RuntimeException.class))})
    })
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UserPermission> createPermission(@RequestBody @Valid UserPermissionCreateDTO createDTO) {
        UserPermission permission = UserPermission.builder()
                .name(createDTO.getName())
                .code(createDTO.getCode())
                .build();
        permission = permissionService.save(permission);
        return ResponseEntity.ok(permission);
    }


    @Operation(summary = "This API used for updating a permission",
            description = "This endpoint was designed for updating a permission")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Permission updated successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserPermission.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class))}),
    })
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserPermission> update(@PathVariable Integer id, @Valid @RequestBody UserPermissionCreateDTO createDTO) {
        UserPermission permission = UserPermission.builder()
                .id(id)
                .name(createDTO.getName())
                .code(createDTO.getCode())
                .build();
        permission = permissionService.update(permission);
        return ResponseEntity.ok(permission);
    }

    @Operation(summary = "This API used for getting a permission",
            description = "This endpoint was designed for getting a permission"
            /*,deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserPermission.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Unique permission code violation",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserPermission> get(@PathVariable Integer id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    @Operation(summary = "This API used for deleting a permission",
            description = "This endpoint was designed for deleting a permission"
            /*,deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission deleted successfully",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserPermission.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    }),
    })
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "This API used for getting all permissions",
            description = "This endpoint was designed for getting all permissions"
            /*,deprecated = true*/)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissions found",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserPermission.class)
                            )
                    }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = RuntimeException.class)
                            )
                    })
    })
    @PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserPermission>> getAll() {
        List<UserPermission> permissionList = permissionService.getAll();
        return ResponseEntity.ok(permissionList);
    }
}
