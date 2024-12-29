/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models;

/**
 *
 * @author Administrator
 */
public interface UserManagement {
    public void register(String name, String username, String password);
    public void login(String username, String password);
    public void updateProfile(int id, String name, String hp);
}
