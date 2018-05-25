package com.apps.morfiwifi.morfi_project_samane.models;

/**
 * User Model
 */
public class UserModel {
    public String Id;
    public String UserName;
    //public List<RoleModel> Roles;
    public int Salary;
    public int FinalSalary;
    public int AbsentDays;
    public int OverTime;
    public int Benefits;

    public String getTitle(){
        return this.UserName;
    }

    public enum kind {
        Student, Master, Technical, Site_Master, Self_Service, Admin;


        @Override
        public String toString() {
            int dis = this.compareTo(kind.Student);
            switch (dis){
                case 0 :
                    return "دانشجو";
                case 1 :
                    return "استاد";
                case 2 :
                    return "فنی";
                case  3 :
                    return "مسئول سایت";
                case 4 :
                    return "سلف";
                case 5:
                    return "ادمین کل";
                default:
                    return "نامشخص";
            }

        }
    }

}
