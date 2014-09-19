require "TP1/Prototyped"
require "TP1/Singleton_Module_Behaviour"
require "TP1/Constructors/Constructors"

module TP

  class PrototypedConstructor

    attr_accessor :prototype, :builder, :extends

    def self.copy(prototype)
      new_proto_constructor = PrototypedConstructor.new(prototype)
      new_proto_constructor.builder = CopyConstructor.new
      new_proto_constructor
    end

    def initialize(prototype = nil, block = nil, &do_end)

      self.prototype = prototype

      if !do_end.nil? then
        self.builder = DoEndConstructor.new(&do_end)
      elsif block.nil? then
        self.builder = HashConstructor.new
      else
        self.builder = ProcConstructor.new(block)
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

      new_object = self.builder.build(new_object, *args.last(self.my_arity))

      new_object
    end

    def arity
      self.my_arity + (self.extends.nil?? 0:self.extends.arity)
    end

    def my_arity
      self.builder.arity
    end

    def extended(*args, &do_end)
      new_constructor = self.class.new(self.prototype, *args, &do_end)
      new_constructor.extends = self
      new_constructor
    end

    def self.create(*args, &do_end)
      self.new(*args, &do_end)
    end

    def with(*args, &do_end)
      self.extended(*args, &do_end)
    end

  end




end