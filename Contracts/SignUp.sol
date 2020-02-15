pragma solidity ^0.5.9;

contract SignUp {
    address private owner;
    uint256 public doctorsCount;
    uint256 public insurance_companies_count;
    uint256 public usersCount;
    uint256 public pharmacyCount;
    uint256 public hashNum;

    mapping(uint256 => address) public doctors_list;
    mapping(uint256 => address) public companies_list;
    mapping(uint256 => address) public users_list;
    mapping(uint256 => File) public file_list;
    mapping(uint256 => address) public pharmacy_list;
    mapping(address => Doctor) private doctors;
    mapping(address => User) private users;
    mapping(address => Pharmacy) private pharmacies;
    mapping(address => Company) private companies;
    mapping(address => BloodBank) private bloodBanks;
    mapping(address => Researcher) private reaserchers;
    mapping(string => bool) private uAadhar;
    mapping(string => bool) private dAadhar;
    mapping(string => bool) private rAadhar;
    mapping(string => bool) private pAadhar;
    mapping(address => string) private authorisation;

    struct User {
        string name;
        string dob;
        string country;
        string city;
        string state;
        string district;
        string locality;
        string houseNum;
        string aadhar;
        string bloodGroup;
        address id;
    }

    struct File {
        string hash;
        address id;
    }

    struct Doctor {
        string name;
        string hospital;
        string aadhar;
        string openTime;
        string closeTime;
        address id;
    }

    struct Pharmacy {
        string name;
        string pharmacy;
        string aadhar;
        string openTime;
        string closeTime;
        address id;
    }

    struct BloodBank {
        string name;
        address id;
    }

    struct Company {
        string name;
        address id;
    }

    struct Researcher {
        string name;
        string researchCenter;
        string aadhar;
        address id;
    }

    constructor() public {
        owner = msg.sender;
        doctorsCount = 0;
        insurance_companies_count = 0;
        usersCount = 0;
        pharmacyCount = 0;
        hashNum = 0;
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "you are not owner");
        _;
    }

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
        string memory _bloodGroup
    ) public {
        User memory u = users[msg.sender];
        require(!(u.id > address(0x0)), "Address already used to make account");
        require(uAadhar[_aadhar] == false, "Aadhaar number already present");
        users[msg.sender] = User({
            name: _name,
            dob: _dob,
            country: _country,
            city: _city,
            state: _state,
            district: _district,
            locality: _locality,
            houseNum: _houseNum,
            aadhar: _aadhar,
            id: msg.sender,
            bloodGroup: _bloodGroup
        });
        uAadhar[_aadhar] = true;
        users_list[usersCount] = msg.sender;
        usersCount++;
    }

    function uploadFile(string memory hash, address id) public {
        file_list[hashNum].id = id;
        file_list[hashNum].hash = hash;
        hashNum++;
    }
    function signupDoctor(
        string memory _name,
        string memory _openTime,
        string memory _closeTime,
        string memory _aadhar,
        string memory _hospital
    ) public {
        Doctor memory d = doctors[msg.sender];
        require(!(d.id > address(0x0)), "Address already used to make account");
        require(dAadhar[_aadhar] == false, "Aadhaar Number already registered");
        doctors[msg.sender] = Doctor({
            name: _name,
            openTime: _openTime,
            closeTime: _closeTime,
            aadhar: _aadhar,
            id: msg.sender,
            hospital: _hospital
        });
        dAadhar[_aadhar] = true;
        doctors_list[doctorsCount] = msg.sender;
        doctorsCount++;
    }

    function signupBloodBank(string memory _name) public {
        BloodBank memory b = bloodBanks[msg.sender];
        require(!(b.id > address(0x0)), "Address already used to make account");
        bloodBanks[msg.sender] = BloodBank({name: _name, id: msg.sender});
    }

    function signupResearcher(
        string memory _name,
        string memory _researchCenter,
        string memory _aadhar
    ) public {
        Researcher memory r = reaserchers[msg.sender];
        require(!(r.id > address(0x0)), "Address already used to make account");
        require(rAadhar[_aadhar] == false, "Aadhaar Number already registered");
        reaserchers[msg.sender] = Researcher({
            name: _name,
            researchCenter: _researchCenter,
            aadhar: _aadhar,
            id: msg.sender
        });
        rAadhar[_aadhar] = true;
        authorisation[msg.sender] = "researcher";
    }

    function signupCompany(string memory _name) public {
        Company memory c = companies[msg.sender];
        require(!(c.id > address(0x0)), "Address already used to make account");
        companies[msg.sender] = Company({name: _name, id: msg.sender});
        companies_list[insurance_companies_count] = msg.sender;
        insurance_companies_count++;
    }

    function signupPharmacy(
        string memory _name,
        string memory _openTime,
        string memory _closeTime,
        string memory _aadhar,
        string memory _pharmacy
    ) public {
        Pharmacy memory p = pharmacies[msg.sender];
        require(!(p.id > address(0x0)), "address already used");
        require(pAadhar[_aadhar] == false, "aadhar already used");
        pharmacies[msg.sender] = Pharmacy({
            name: _name,
            pharmacy: _pharmacy,
            aadhar: _aadhar,
            id: msg.sender,
            openTime: _openTime,
            closeTime: _closeTime
        });
        pAadhar[_aadhar] = true;
        pharmacy_list[pharmacyCount] = msg.sender;
        pharmacyCount++;
    }

    function getDoctorDetails(address id)
        public
        view
        returns (string memory, string memory, string memory, string memory)
    {
        return (
            doctors[id].name,
            doctors[id].hospital,
            doctors[id].openTime,
            doctors[id].closeTime
        );
    }

    function getCompanyDetails(address id) public view returns (string memory) {
        return companies[id].name;
    }

    function getUserDetails(address id)
        public
        view
        returns (
            string memory,
            string memory,
            string memory,
            string memory,
            string memory,
            string memory
        )
    {
        return (
            users[id].country,
            users[id].state,
            users[id].district,
            users[id].city,
            users[id].locality,
            users[id].houseNum
        );
    }

    function getUserHash(uint256 itr) public view returns (string memory) {
        return (file_list[itr].hash);
    }

    function getUserAddressHash(uint256 itr) public view returns (address id) {
        return (file_list[itr].id);
    }

    function getPharmacyDetails(address id)
        public
        view
        returns (string memory, string memory, string memory, string memory)
    {
        return (
            pharmacies[id].name,
            pharmacies[id].pharmacy,
            pharmacies[id].openTime,
            pharmacies[id].closeTime
        );
    }

    function getAuthorizeDetails(address id)
        public
        view
        returns (string memory)
    {
        return authorisation[id];
    }

    function editUserDetails(
        string memory _locality,
        string memory _district,
        string memory _state,
        string memory _country,
        string memory _city,
        string memory _houseNum
    ) public {
        if (keccak256(abi.encodePacked(_locality)) != keccak256(""))
            users[msg.sender].locality = _locality;
        if (keccak256(abi.encodePacked(_district)) != keccak256(""))
            users[msg.sender].district = _district;
        if (keccak256(abi.encodePacked(_state)) != keccak256(""))
            users[msg.sender].state = _state;
        if (keccak256(abi.encodePacked(_country)) != keccak256(""))
            users[msg.sender].country = _country;
        if (keccak256(abi.encodePacked(_city)) != keccak256(""))
            users[msg.sender].city = _city;
        if (keccak256(abi.encodePacked(_houseNum)) != keccak256(""))
            users[msg.sender].houseNum = _houseNum;
    }

    function editDoctorDetails(
        string memory _openTime,
        string memory _closeTime,
        string memory _hospital
    ) public {
        if (keccak256(abi.encodePacked(_openTime)) != keccak256(""))
            doctors[msg.sender].openTime = _openTime;
        if (keccak256(abi.encodePacked(_closeTime)) != keccak256(""))
            doctors[msg.sender].closeTime = _closeTime;
        if (keccak256(abi.encodePacked(_hospital)) != keccak256(""))
            doctors[msg.sender].hospital = _hospital;
    }

    function editPharmacyDetails(
        string memory _pharmacy,
        string memory _openTime,
        string memory _closeTime
    ) public {
        if (keccak256(abi.encodePacked(_pharmacy)) != keccak256(""))
            pharmacies[msg.sender].pharmacy = _pharmacy;
        if (keccak256(abi.encodePacked(_openTime)) != keccak256(""))
            pharmacies[msg.sender].openTime = _openTime;
        if (keccak256(abi.encodePacked(_closeTime)) != keccak256(""))
            pharmacies[msg.sender].closeTime = _closeTime;
    }

    function getUsersCount() public view returns (uint256) {
        return usersCount;
    }

    function getUserBloodGroup(uint256 itr)
        public
        view
        returns (string memory)
    {
        return users[users_list[itr]].bloodGroup;
    }

    function getUserAddress(uint256 itr) public view returns (address) {
        return users_list[itr];
    }
}
