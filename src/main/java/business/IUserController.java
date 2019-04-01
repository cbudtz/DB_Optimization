package business;

import data.IUserDTO;

import java.util.List;

public interface IUserController {
    IUserDTO getUser(int id);

    List<IUserDTO> getUsers();
}
