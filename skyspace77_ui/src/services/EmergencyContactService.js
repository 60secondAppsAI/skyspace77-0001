import http from "../http-common"; 

class EmergencyContactService {
  getAllEmergencyContacts(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/emergencyContact/emergencyContacts`, searchDTO);
  }

  get(emergencyContactId) {
    return this.getRequest(`/emergencyContact/${emergencyContactId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/emergencyContact?field=${matchData}`, null);
  }

  addEmergencyContact(data) {
    return http.post("/emergencyContact/addEmergencyContact", data);
  }

  update(data) {
  	return http.post("/emergencyContact/updateEmergencyContact", data);
  }
  
  uploadImage(data,emergencyContactId) {
  	return http.postForm("/emergencyContact/uploadImage/"+emergencyContactId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new EmergencyContactService();
