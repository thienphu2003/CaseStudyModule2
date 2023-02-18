package Worker;

public class Worker {
    private String name;
    private String id;
    private String phoneNumber;
    private Type type;
    private int totalShift;
    private int totalSalary;


    public boolean award()
    {
        if(this.totalShift>=25)
        {
            return true;
        }
        return false;
    }


    public Worker(WorkerBuilder builder)
    {
        this.name=builder.name;
        this.id=builder.id;
        this.phoneNumber=builder.phoneNumber;
        this.type=builder.type;
        this.totalShift=builder.totalShift;
        this.totalSalary=builder.totalSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getTotalShift() {
        return totalShift;
    }

    public void setTotalShift(int totalShift) {
        this.totalShift = totalShift;
    }
    public static class WorkerBuilder
    {
        private String name;
        private String id;
        private String phoneNumber;
        private Type type;
        private int totalShift;
        private int totalSalary;
        public WorkerBuilder(){}
        public WorkerBuilder name(String name)
        {
            this.name=name;
            return this;
        }
        public WorkerBuilder id(String id)
        {
            this.id=id;
            return this;
        }
        public WorkerBuilder phoneNumber(String phoneNumber)
        {
            this.phoneNumber=phoneNumber;
            return this;
        }
        public WorkerBuilder type(Type type)
        {
            this.type=type;
            return this;
        }
        public WorkerBuilder totalShift(int totalShift)
        {
            this.totalShift=totalShift;
            return this;
        }

        public WorkerBuilder totalSalary(int totalSalary)
        {
            this.totalSalary=totalSalary;
            return this;
        }
        public Worker build()
        {
            return new Worker(this);
        }
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type=" + type +
                ", totalShift=" + totalShift +
                ", totalSalary=" + totalSalary +
                '}';
    }

    public String toFile()
    {
        return name+","+id+","+phoneNumber+","+type+","+totalShift+","+totalSalary;
    }


    public int getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary = totalSalary;
    }
}
