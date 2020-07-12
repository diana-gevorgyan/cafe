import React, {useEffect, useState} from 'react';
import UsersComponent from "./UsersComponent";
import {createNewUser, getAllUsers} from "../../../client/BackendApiClient";

const UsersContainer = () => {
  const [users, setUsers] = useState([]);
  const [errors, setErrors] = useState(null);
  const [newUserName, setNewUserName] = useState("");
  const [newUserEmail, setNewUserEmail] = useState("");
  const [newUserType, setNewUserType] = useState("");
  const [newUserPassword, setNewUserPassword] = useState("");
  const [isModalOpen, setModalOpen] = useState(false);

  useEffect(() => {
    getAllUsers().then((res) => setUsers(res.data))
  }, [])

  const saveNewUser = () => {
    createNewUser(newUserName, newUserEmail, newUserPassword, newUserType)
      .then(() => getAllUsers().then((res) => {
        setUsers(res.data);
        setModalOpen(false)
      }))
      .catch((error) => {
        const {response: {status, data}} = error;
        setErrors(data);
      })
  }

  return (
    <div>
      <UsersComponent
        users={users}
        newUserName={newUserName}
        setNewUserName={setNewUserName}
        newUserEmail={newUserEmail}
        setNewUserEmail={setNewUserEmail}
        newUserType={newUserType}
        setNewUserType={setNewUserType}
        newUserPassword={newUserPassword}
        setNewUserPassword={setNewUserPassword}
        saveNewUser={saveNewUser}
        isModalOpen={isModalOpen}
        setModalOpen={setModalOpen}
        errors={errors}
      />
    </div>
  );
};

export default UsersContainer;