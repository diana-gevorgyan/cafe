import React, {useEffect, useState} from 'react';
import TablesComponent from "./TablesComponent";
import {changeTableAssignee, getAllTables, getAllWaitersList, registerNewTable} from "../../../client/BackendApiClient";

const TablesContainer = () => {
  const [newTableNumber, setNewTableNumber] = useState("");
  const [errors, setErrors] = useState(null);
  const [newTableAssignedUserId, setNewTableAssignedUserId] = useState("");
  const [isModalOpen, setModalOpen] = useState(false);

  const [tables, setTables] = useState([]);
  const [allWaiterUsers, setAllWaiterUsers] = useState([]);

  useEffect(() => {
    getAllTablesMapped();
    getAllWaitersList().then((res) => setAllWaiterUsers(res.data))
  }, []);

  const getAllTablesMapped = () => (
    getAllTables().then((res) => {
      const parsedTablesData = mapData(res.data)
        .sort((a, b) => (a.id - b.id));
      setTables(parsedTablesData);
    })
  );

  const createData = (id, number, waiter) => {
    return {id, number, waiter};
  }

  const mapData = (tablesData) => (
    tablesData.map((table) => (
      createData(table.id, `Table ${table.number}`, table.user)
    ))
  )

  const saveNewTable = () => {
    registerNewTable(newTableNumber, newTableAssignedUserId)
      .then(() => {
          getAllTablesMapped();
          setModalOpen(false)
        }
      )
      .catch((error) => {
        const {response: {data}} = error;
        setErrors(data);
      })

  }

  const changeAssignedWaiter = (e, tableId) => {
    changeTableAssignee(tableId, e.target.value)
      .then(() => getAllTablesMapped())
  }

  return (
    <TablesComponent
      tables={tables}
      newTableNumber={newTableNumber}
      setNewTableNumber={setNewTableNumber}
      newTableAssignedUserId={newTableAssignedUserId}
      setNewTableAssignedUserId={setNewTableAssignedUserId}
      isModalOpen={isModalOpen}
      setModalOpen={setModalOpen}
      allWaiterUsers={allWaiterUsers}
      saveNewTable={saveNewTable}
      errors={errors}
      changeAssignedWaiter={changeAssignedWaiter}
    />
  );
};

export default TablesContainer;