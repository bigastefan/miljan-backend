package hustlebuddy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hustlebuddy.models.Permission;
import hustlebuddy.repositories.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	PermissionRepository permissionRepository;
	
	public Iterable<Permission> getAllPermissions() {
		return permissionRepository.findAll();
	}
	
	public Optional<Permission> getPermissionById(Long id) {
		return permissionRepository.findById(id);
	}
	
	public Optional<Permission> getPermissionByRoleType(String roleType) {
		return permissionRepository.findByRoleType(roleType);
	}
	
	public void addPermission(Permission permission) {
		permissionRepository.save(permission);
	}
	
	public void removePermission(Long id) {
		Optional<Permission> permission = permissionRepository.findById(id);
		if(permission.isPresent()) {
			permissionRepository.delete(permission.get());
		}
	}
	
	public void updatePermission(Long id, Permission permission) {
		Optional<Permission> per = permissionRepository.findById(id);
		if(per.isPresent()) {
			permission.setId(per.get().getId());
			permissionRepository.save(permission);
		}
	}
}
