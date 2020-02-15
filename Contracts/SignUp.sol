pragma solidity ^0.5.9;

contract SignUp {
    address private owner;
    uint256 public doctorsCount;
    uint256 public insurance_companies_count;
    uint256 public usersCount;
    uint256 public pharmacyCount;
    uint256 public authorize_list_count;

    mapping(uint256 => address) public doctors_list;
    mapping(uint256 => address) public companies_list;
    mapping(uint256 => address) public users_list;
    mapping(uint256 => address) public authorize_list;
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
        string hash;
        string city;
        string state;
        string district;
        string locality;
        string houseNum;
        string aadhar;
        string bloodGroup;
        address id;
    }

    struct Doctor {
        string name;
        string hospital;
        string aadhar;
        string openTime;
        string closeTime;
        address id;
        int256 isAuthorized;
    }

    struct Pharmacy {
        string name;
        string pharmacy;
        string aadhar;
        string openTime;
        string closeTime;
        address id;
        int256 isAuthorized;
    }

    struct BloodBank {
        string name;
        address id;
        int256 isAuthorized;
    }

    struct Company {
        string name;
        address id;
        int256 isAuthorized;
    }

    struct Researcher {
        string name;
        string researchCenter;
        string aadhar;
        address id;
        int256 isAuthorized;
    }

    constructor() public {
        owner = msg.sender;
        doctorsCount = 0;
        insurance_companies_count = 0;
        usersCount = 0;
        authorize_list_count = 0;
        pharmacyCount = 0;
    }

    modifier onlyOwner() {
        require(msg.sender == owner, "you are not owner");
        _;
    }

    function authorize(address person, string memory name) public onlyOwner {
        if (keccak256(abi.encodePacked(name)) == keccak256("doctor")) {
            doctors[person].isAuthorized = 1;
            doctors_list[doctorsCount] = person;
            doctorsCount++;
        }
        if (keccak256(abi.encodePacked(name)) == keccak256("researcher"))
            reaserchers[person].isAuthorized = 1;
        if (keccak256(abi.encodePacked(name)) == keccak256("bloodBank"))
            reaserchers[person].isAuthorized = 1;
        if (keccak256(abi.encodePacked(name)) == keccak256("pharmacy")) {
            pharmacies[person].isAuthorized = 1;
            pharmacy_list[pharmacyCount] = person;
            pharmacyCount++;
        }
        if (keccak256(abi.encodePacked(name)) == keccak256("company")) {
            companies[person].isAuthorized = 1;
            companies_list[insurance_companies_count] = person;
            insurance_companies_count++;
        }
    }

    function reject(address person, string memory name) public onlyOwner {
        if (keccak256(abi.encodePacked(name)) == keccak256("doctor")) {
            doctors[person].isAuthorized = 2;
        }
        if (keccak256(abi.encodePacked(name)) == keccak256("researcher"))
            reaserchers[person].isAuthorized = 2;
        if (keccak256(abi.encodePacked(name)) == keccak256("bloodBank"))
            reaserchers[person].isAuthorized = 2;
        if (keccak256(abi.encodePacked(name)) == keccak256("pharmacy"))
            pharmacies[person].isAuthorized = 2;
        if (keccak256(abi.encodePacked(name)) == keccak256("company"))
            companies[person].isAuthorized = 2;
    }

    function isAuthorized(address person, string memory name)
        public
        view
        onlyOwner
        returns (int256)
    {
        if (keccak256(abi.encodePacked(name)) == keccak256("doctor")) {
            return doctors[person].isAuthorized;
        }
        if (keccak256(abi.encodePacked(name)) == keccak256("researcher"))
            return reaserchers[person].isAuthorized;
        if (keccak256(abi.encodePacked(name)) == keccak256("bloodBank"))
            return reaserchers[person].isAuthorized;
        if (keccak256(abi.encodePacked(name)) == keccak256("pharmacy"))
            return pharmacies[person].isAuthorized;
        if (keccak256(abi.encodePacked(name)) == keccak256("company"))
            return companies[person].isAuthorized;
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
        string memory _bloodGroup,
        string memory _hash
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
            bloodGroup: _bloodGroup,
            hash: _hash
        });
        uAadhar[_aadhar] = true;
        users_list[usersCount] = msg.sender;
        usersCount++;
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
            isAuthorized: 0,
            hospital: _hospital
        });
        dAadhar[_aadhar] = true;
        authorize_list[authorize_list_count] = msg.sender;
        authorize_list_count++;
        authorisation[msg.sender] = "doctor";
    }

    function signupBloodBank(string memory _name) public {
        BloodBank memory b = bloodBanks[msg.sender];
        require(!(b.id > address(0x0)), "Address already used to make account");
        bloodBanks[msg.sender] = BloodBank({
            name: _name,
            id: msg.sender,
            isAuthorized: 0
        });
        authorize_list[authorize_list_count] = msg.sender;
        authorize_list_count++;
        authorisation[msg.sender] = "bloodBank";
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
            id: msg.sender,
            isAuthorized: 0
        });
        rAadhar[_aadhar] = true;
        authorize_list[authorize_list_count] = msg.sender;
        authorize_list_count++;
        authorisation[msg.sender] = "researcher";
    }

    function signupCompany(string memory _name) public {
        Company memory c = companies[msg.sender];
        require(!(c.id > address(0x0)), "Address already used to make account");
        companies[msg.sender] = Company({
            name: _name,
            id: msg.sender,
            isAuthorized: 0
        });
        authorize_list[authorize_list_count] = msg.sender;
        authorize_list_count++;
        authorisation[msg.sender] = "company";
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
            isAuthorized: 0,
            openTime: _openTime,
            closeTime: _closeTime
        });
        authorize_list[authorize_list_count] = msg.sender;
        authorize_list_count++;
        authorisation[msg.sender] = "pharmacy";
        pAadhar[_aadhar] = true;
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

    function getUserHash(address id) public view returns (string memory) {
        return (users[id].hash);
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
        string memory _houseNum,
        string memory _hash
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
        if (keccak256(abi.encodePacked(_hash)) != keccak256(""))
            users[msg.sender].hash = _hash;
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
