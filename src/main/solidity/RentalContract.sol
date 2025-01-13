// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract RentalContract {
    struct Contract {
        address landlord;
        address tenant;
        uint256 amount;
        string startDate;
        string endDate;
    }

    mapping(uint256 => Contract) public contracts;
    uint256 public contractCount;

    event ContractCreated(uint256 id, address landlord, address tenant, uint256 amount);

    function createContract(
        address _tenant,
        uint256 _amount,
        string memory _startDate,
        string memory _endDate
    ) public {
        contracts[contractCount] = Contract({
            landlord: msg.sender,
            tenant: _tenant,
            amount: _amount,
            startDate: _startDate,
            endDate: _endDate
        });

        emit ContractCreated(contractCount, msg.sender, _tenant, _amount);
        contractCount++;
    }

    function getContract(uint256 _id) public view returns (
        address landlord,
        address tenant,
        uint256 amount,
        string memory startDate,
        string memory endDate
    ) {
        Contract memory c = contracts[_id];
        return (c.landlord, c.tenant, c.amount, c.startDate, c.endDate);
    }
}
