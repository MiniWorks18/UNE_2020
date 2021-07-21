#include<list>
#include<algorithm>
#include<iostream>
#include<string>
#include<fstream>
#include<vector>
#include<sstream>
#include<iterator>
using namespace std;

int menu()
{
	int option;
	cout << endl;
	cout << "Enter one of the following options:" << endl;
	cout << "1. load reservations from file:" << endl;
	cout << "2. reserve a ticket" << endl;
	cout << "3. cancel a reservation" << endl;
	cout << "4. check reservation" << endl;
	cout << "5. display passenger list" << endl;
	cout << "6. save passenger list" << endl;
	cout << "7. exit" << endl << endl;
	cin >> option;
	cin.get();
	return option;
}

class Passenger {
public:
	Passenger() {fname = lname = dest = "";}
	Passenger(string f = "null", string l = "null", string d = "null") {
		fname = f;
		lname = l;
		dest = d;
	}
	string fname, lname, dest;
	string getFirstName() {return fname;}
	string getLastName() {return lname;}
	string getDestination() {return dest;}
	bool operator == (const Passenger& p) const { return fname == p.fname;}
};

void replaceAll(string &line, string toSearch, string replaceStr)
{
	size_t pos = line.find(toSearch);
	while( pos != string::npos)
	{
		line.replace(pos, toSearch.size(), replaceStr);
		pos = line.find(toSearch, pos + replaceStr.size());
	}
};

void read_from_file(list<Passenger>& flist, string filename)
{
	string name;
	ifstream input(filename.c_str());
	while (input >> name)
	{
		flist.push_back(Passenger(name));
	}
	input.close();
}

void insert(list<Passenger>& flist, string fname, string lname = "null",
string dest = "null") {
flist.push_back(Passenger(fname, lname, dest));
}

void remove(list<Passenger>& flist, string fname, string lname = "null") {
	int i = 0;
	for (Passenger p : flist) {
		if (p.getFirstName() == fname && p.getLastName() == lname) {
			flist.remove(p);
			break;
		}
		i++;
	}
}

bool check_reservation(list<Passenger>& flist, string fname,
	string lname = "null") {
	for (Passenger p : flist) {
		if (p.getFirstName() == fname && p.getLastName() == lname){
			return true;
		}
	}
	return false;
}

void display_list(list<Passenger>& flist) {
	string fname, lname, dest;
	for (Passenger p : flist) {
		if (p.getFirstName() != "null") cout << p.getFirstName() << endl;
		if (p.getLastName() != "null") cout << p.getLastName() << endl;
		if (p.getDestination() != "null") cout << p.getDestination() << endl;
	}

}

void save_to_file(list<Passenger>& flist, string filename)
{
	ofstream output(filename.c_str());
	for (Passenger p : flist) {
		string fname = p.getFirstName();
		replaceAll(fname, " ", "_");
		string lname = p.getLastName();
		replaceAll(lname, " ", "_");
		string dest = p.getDestination();
		replaceAll(dest, " ", "_");
		output << p.getFirstName() << " " << p.getLastName()
		<< " " << p.getDestination() << endl;
	}
	output.close();
}
