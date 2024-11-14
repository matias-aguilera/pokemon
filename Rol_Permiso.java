public class Rol_Permiso {
    
    private String ID_Permiso;
    private String ID_Rol;

    public Rol_Permiso(String ID_Permiso, String ID_Rol) {
        this.ID_Permiso = ID_Permiso;
        this.ID_Rol = ID_Rol;
    }

    public String getID_Permiso() {
        return ID_Permiso;
    }

    public void setID_Permiso(String ID_Permiso) {
        this.ID_Permiso = ID_Permiso;
    }

    public String getID_Rol() {
        return ID_Rol;
    }

    public void setID_Rol(String ID_Rol) {
        this.ID_Rol = ID_Rol;
    }

}
