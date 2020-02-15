pragma solidity ^0.5.9;

contract Processor {
    address private owner;
    address private parent;
    uint256 public presCount;
    uint256 public insuranceCount;
    uint256 public blood_request_count;
    mapping(uint256 => bloodRequest) public blood_request_global;
    mapping(uint256 => prescription) public presGlobal;
    mapping(uint256 => insurance) public insuranceGlobal;

    SignUp signup;

    struct bloodRequest {
        address id_user;
        address id_bank;
        uint256 situation;
    }

    struct prescription {
        string diseases;
        string medicines;
        uint256 duration;
        address id_pharmacy;
        address id_user;
        address id_doctor;
        uint256 situation;
    }

    struct insurance {
        address id_user;
        address id_company;
        uint256 duration;
    }

    constructor(address c) public {
        owner = msg.sender;
        presCount = 0;
        insuranceCount = 0;
        blood_request_count = 0;
        parent = c;
        signup = SignUp(c);
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "you are not owner");
        _;
    }

    function requestDoctor(address id_doctor, uint256 time) public {
        prescription memory p;
        p.duration = now + (time * (60 seconds));
        p.id_doctor = id_doctor;
        p.id_user = msg.sender;
        p.situation = 0;
        presGlobal[presCount] = p;
        presCount++;
    }

    function requestCompany(address id_company, uint256 time) public {
        insurance memory i;
        i.duration = now + (time * (60 seconds));
        i.id_company = id_company;
        i.id_user = msg.sender;
        insuranceGlobal[insuranceCount] = i;
        insuranceCount++;
    }

    function requestPharmacy(uint256 itr, address id) public {
        require(
            presGlobal[itr].situation == 1,
            "You are not prescribed by doctor or have medicine already"
        );
        presGlobal[itr].id_pharmacy = id;
    }

    function diagnosePatient(
        uint256 itr,
        string memory diseases,
        string memory medicines
    ) public {
        // require(
        //     presGlobal[itr].duration < now,
        //     "Time passed away, you are no longer authorized"
        // );
        presGlobal[itr].diseases = diseases;
        presGlobal[itr].medicines = medicines;
        presGlobal[itr].situation = 1;
    }

    function giveMedicines(uint256 itr) public {
        // require(
        //     presGlobal[itr].duration < now,
        //     "Time passed away, you are no longer authorized"
        // );
        presGlobal[itr].situation = 2;
    }

    function getMedicine(uint256 itr) public view returns (string memory) {
        return presGlobal[itr].medicines;
    }

    function getDiseases(uint256 itr) public view returns (string memory) {
        return presGlobal[itr].diseases;
    }

    function requestBlood(string memory _bloodGroup) public {
        uint256 usersCount = signup.getUsersCount();
        for (uint256 i = 0; i < usersCount; i++) {
            if (
                keccak256(abi.encodePacked(signup.getUserBloodGroup(i))) ==
                keccak256(abi.encodePacked(_bloodGroup))
            ) {
                blood_request_global[blood_request_count].situation = 0;
                blood_request_global[blood_request_count].id_user = signup
                    .getUserAddress(i);
                blood_request_global[blood_request_count].id_bank = msg.sender;
                blood_request_count++;
            }
        }
    }

    function getBloodDetails(uint256 itr)
        public
        view
        returns (address, address, uint256)
    {
        return (
            blood_request_global[itr].id_user,
            blood_request_global[itr].id_bank,
            blood_request_global[itr].situation
        );
    }

    function respondBloodRequest(uint256 itr, uint256 response) public {
        blood_request_global[itr].situation = response;
    }

    function acceptedBloodRequest(uint256 itr) public {
        blood_request_global[itr].situation = 2;
    }

    function getPrescription(uint256 itr)
        public
        view
        returns (address, address, address, uint256)
    {
        return (
            presGlobal[itr].id_pharmacy,
            presGlobal[itr].id_user,
            presGlobal[itr].id_doctor,
            presGlobal[itr].situation
        );
    }
}

contract SignUp {
    function authorize(address person, string memory name) public;
    function reject(address person, string memory name) public;
    function signupUser(
        string memory _name,
        string memory _dob,
        string memory _country,
        string memory _city,
        string memory _state,
        string memory _district,
        string memory _locality,
        string memory _houseNum,
        string memory _aadhar,
        string memory _bloodGroup,
        string memory _hash
    ) public;
    function signupDoctor(
        string memory _name,
        string memory _openTime,
        string memory _closeTime,
        string memory _aadhar,
        string memory _hospital
    ) public;
    function signupBloodBank(string memory _name) public;
    function signupResearcher(
        string memory _name,
        string memory _researchCenter,
        string memory _aadhar
    ) public;
    function signupCompany(string memory _name) public;
    function signupPharmacy(
        string memory _name,
        string memory _pharmacy,
        string memory _aadhar,
        string memory _openTime,
        string memory _closeTime
    ) public;
    function getDoctorDetails(address id)
        public
        view
        returns (string memory, string memory, string memory, string memory);
    function editUserDetails(
        string memory _locality,
        string memory _district,
        string memory _state,
        string memory _country,
        string memory _city,
        string memory _houseNum,
        string memory _hash
    ) public;
    function editDoctorDetails(
        string memory _openTime,
        string memory _closeTime,
        string memory _hospital
    ) public;
    function editPharmacyDetails(string memory _pharmacy) public;
    function getCompanyDetails(address id) public view returns (string memory);
    function getUserHash(address id) public view returns (string memory);
    function getUsersCount() public view returns (uint256);
    function getUserBloodGroup(uint256 itr) public view returns (string memory);
    function getUserDetails(address id)
        public
        view
        returns (string memory, string memory, string memory, string memory);
    function getUserAddress(uint256 itr) public view returns (address);
    function getAuthorizeDetails(address id)
        public
        view
        returns (string memory);
    function isAuthorized(address person, string memory name)
        public
        view
        returns (bool);
}
