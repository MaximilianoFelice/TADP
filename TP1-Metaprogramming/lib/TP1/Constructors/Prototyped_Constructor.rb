require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"
require "TP1/Constructors/Constructors"

module TP

  class PrototypedConstructor

    attr_accessor :prototype, :builder, :extends, :base_prototype

    def self.copy(prototype)
      new_proto_constructor = PrototypedConstructor.new(prototype)
      new_proto_constructor.builder = CopyConstructor.new
      new_proto_constructor
    end

    def initialize(prototype = nil, block = nil, &do_end)

      self.prototype = prototype

      self.base_prototype = Object.new
      self.base_prototype.extend Prototyped

      if !do_end.nil? then
        self.builder = DoEndConstructor.new(&do_end)
        self.base_prototype = self.builder.build(self.base_prototype)
      elsif block.nil? then
        self.builder = HashConstructor.new
        self.base_prototype = self.builder.build(self.base_prototype, {})
      else
        self.builder = ProcConstructor.new(block)
        self.base_prototype = self.builder.build(self.base_prototype)
      end

    end

    def new(*args)

      if extends.nil? then
        new_object = Object.new
        new_object.extend Prototyped
        if (!self.prototype.nil?) then new_object.set_prototype(self.prototype) end
      else
        new_object = self.extends.new(*args.first(self.extends.arity))
      end

      new_object.set_prototype(base_prototype)

      new_object = self.builder.build(Property_Injector.new(new_object), *args.last(self.my_arity))

      if (new_object.class == Property_Injector) then
          new_object = new_object.building_object
      end

      new_object
    end

    def arity
      self.my_arity + (self.extends.nil?? 0:self.extends.arity)
    end

    def my_arity
      self.builder.arity
    end

    def extended(*args, &do_end)
      new_constructor = self.class.new(nil, *args, &do_end)
      new_constructor.extends = self
      new_constructor
    end

    def self.create(*args, &do_end)
      self.new(*args, &do_end)
    end

    def with(*args, &do_end)
      self.extended(*args, &do_end)
    end

    def with_properties(*properties)
      new_prop_constructor = self.class.new()
      new_prop_constructor.builder = WithPropertiesConstructor.new(*properties)
      base_prototype = Object.new
      base_prototype.extend Prototyped
      new_prop_constructor.base_prototype = new_prop_constructor.builder.build(base_prototype)
      new_prop_constructor.extends = self
      new_prop_constructor
    end


  end


  # TODO: Description here
  class Property_Injector
    attr_accessor :building_object

    def initialize(new_object)
      self.building_object = new_object
    end

    def prototypes
      self.building_object.prototypes
    end

    def method_missing(method, *args, &block)
      if (method =~ /(.*)=/) or (method =~ /set_property/) then   #It's a property setting
        self.building_object.send(method, *args, &block)
      end
    end
  end


end