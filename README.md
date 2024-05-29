## About
This is a simple implementation of an AVL Tree made for educational purposes.

## AVL Tree Specification

### Description

An AVL tree is required to use two keys:
1. **Navigation Key**: Used for navigation and maintaining the balance of the tree.
2. **Associated Key**: Associated with the navigation key and contains data about a person.

### Specifications

The ADT (Abstract Data Type) must store, at a minimum, the following information for each person:
- **ID number (DNI)**
- **First name**
- **Last name**
- **Address**
- **Phone number**

### Functional Requirements

1. **Insertion and Search**:
   - Implement methods to insert and search for persons in the AVL tree using the navigation key.

2. **Tree Balancing**:
   - Include a method to maintain the balance of the tree after each insertion or deletion, following the properties of the AVL tree.

3. **Basic Operations**:
   - Methods to delete nodes, find the minimum and maximum, and perform in-order, pre-order, and post-order traversals.

### Example Usage

Each tree node will contain a data structure of a person associated with the navigation key. The tree should automatically adjust to maintain its balance after each insertion or deletion operation.

### Additional Notes

- Ensure that all operations maintain the balance of the AVL tree.
